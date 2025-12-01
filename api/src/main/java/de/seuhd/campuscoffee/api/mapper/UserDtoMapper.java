package de.seuhd.campuscoffee.api.mapper;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;

public interface UserDtoMapper {
    //TODO: Implement user DTO mapper
    static User toDomain(UserDto dto) {
        if (dto == null) return null;

        return new User(
                dto.id(),
                dto.createdAt(),
                dto.updatedAt(),
                dto.loginName(),
                dto.emailAddress(),
                dto.firstName(),
                dto.lastName()

        );
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

