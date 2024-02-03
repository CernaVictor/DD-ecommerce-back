package com.ecommerce.ecommerce.mappers;

import com.ecommerce.ecommerce.dtos.UserDTO;
import com.ecommerce.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}