package de.seuhd.campuscoffee.api.mapper;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;

public interface UserDtoMapper {
    static User toDomain(UserDto dto) {
        if (dto == null) return null;
        return User.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .updatedAt(dto.updatedAt())
                .loginName(dto.loginName())
                .emailAddress(dto.emailAddress())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
    }

    static UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.id())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .loginName(user.loginName())
                .emailAddress(user.emailAddress())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .build();
    }
}
