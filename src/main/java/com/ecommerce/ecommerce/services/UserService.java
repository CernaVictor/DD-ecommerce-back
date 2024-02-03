package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dtos.AuthUserDTO;
import com.ecommerce.ecommerce.dtos.LoginDTO;
import com.ecommerce.ecommerce.dtos.RegisterDTO;
import com.ecommerce.ecommerce.entities.Address;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.exceptions.InvalidTokenException;
import com.ecommerce.ecommerce.exceptions.NotFoundException;
import com.ecommerce.ecommerce.mappers.AddressMapper;
import com.ecommerce.ecommerce.repositories.AddressRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import com.ecommerce.ecommerce.mappers.UserMapper;
import com.ecommerce.ecommerce.exceptions.AuthenticationException;
import com.ecommerce.ecommerce.utils.EmailUtil;
import com.ecommerce.ecommerce.utils.JwtUtil;
import com.ecommerce.ecommerce.validations.LoginValidation;
import com.ecommerce.ecommerce.validations.RegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final RegisterValidation registerValidation;
    private final LoginValidation loginValidation;

    private final EmailUtil emailUtil;

    private final JwtUtil jwtUtil;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, UserMapper userMapper, AddressMapper addressMapper, RegisterValidation registerValidation, LoginValidation loginValidation, EmailUtil emailUtil, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.registerValidation = registerValidation;
        this.loginValidation = loginValidation;
        this.emailUtil = emailUtil;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(RegisterDTO registerDTO) {
        registerValidation.registerValidation(registerDTO);

        User newUser = userMapper.toUser(registerDTO.getUser());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        Address deliveryAddress = addressMapper.toAddress(registerDTO.getDeliveryAddress());
        addressRepository.save(deliveryAddress);
        newUser.setDeliveryAddress(deliveryAddress);

        if (registerDTO.getDeliveryAddress().equals(registerDTO.getBillingAddress())) {
            newUser.setBillingAddress(deliveryAddress);
        } else {
            Address billingAddress = addressMapper.toAddress(registerDTO.getBillingAddress());
            addressRepository.save(billingAddress);
            newUser.setBillingAddress(billingAddress);
        }

        userRepository.save(newUser);

        return newUser;
    }

    public AuthUserDTO loginUser(LoginDTO loginDTO) {
        loginValidation.loginValidation(loginDTO);

        User user = userRepository.findByEmail(loginDTO.getEmail());

        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
             return new AuthUserDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),user.getIsAdmin());
        } else {
            throw new AuthenticationException("Invalid email or password");
        }
    }

    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        String token = jwtUtil.generateResetPasswordToken(email);

        user.setResetToken(token);
        user.setResetTokenExpiry(new Date(System.currentTimeMillis() + expirationMs));

        userRepository.save(user);

        try {
            emailUtil.sendSetPasswordEmail(email, token);
        } catch (Exception e) {
            throw new RuntimeException("Unable to send set password email");
        }
        return "Check email to reset password";
    }

    public String setPassword(String token, String newPassword) {
        String email = jwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        if (user.getResetToken() == null || !user.getResetToken().equals(token) || user.getResetTokenExpiry().getTime() <= System.currentTimeMillis()) {
            throw new InvalidTokenException("Invalid or expired token.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);

        userRepository.save(user);

        return "New password set successfully";
    }
}
