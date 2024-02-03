package com.ecommerce.ecommerce.mappers;

import com.ecommerce.ecommerce.dtos.AddressDTO;
import com.ecommerce.ecommerce.dtos.UserDTO;
import com.ecommerce.ecommerce.entities.Address;
import com.ecommerce.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class, AddressMapper.class})
public interface RegisterMapper {
    User toUser(UserDTO userDTO);
    Address toAddress(AddressDTO addressDTO);
    UserDTO toUserDTO(User user);
    AddressDTO toAddressDTO(Address address);
}