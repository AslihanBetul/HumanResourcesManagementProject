package com.java14.mapper;

import com.java14.dto.request.RegisterAdminRequestDto;
import com.java14.dto.request.RegisterManagerRequestDto;
import com.java14.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
 AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth RegisterAdminDtoToAuth(RegisterAdminRequestDto dto);

    Auth RegisterManagerDtoToAuth(RegisterManagerRequestDto dto);

}
