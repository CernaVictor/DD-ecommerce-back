package com.ecommerce.ecommerce.mappers;

import com.ecommerce.ecommerce.dtos.AddressDTO;
import com.ecommerce.ecommerce.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
}