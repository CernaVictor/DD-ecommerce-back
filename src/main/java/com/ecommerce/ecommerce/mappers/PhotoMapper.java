package com.ecommerce.ecommerce.mappers;

import com.ecommerce.ecommerce.dtos.PhotoDTO;
import com.ecommerce.ecommerce.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PhotoMapper {

    Photo toPhoto(PhotoDTO photoDTO);

    PhotoDTO toPhotoDTO(Photo photo);
}