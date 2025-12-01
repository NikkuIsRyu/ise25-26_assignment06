package de.seuhd.campuscoffee.domain.ports;

import java.util.List;
import de.seuhd.campuscoffee.api.dtos.UserDto;

public interface UserService {
    //TODO: Define user service interface

    UserDto getbyId(Long id);
    UserDto create(UserDto userdto);
    UserDto update (Long id, UserDto userdto);
    List<UserDto> getAllUsers();
    void delete(Long id);
}
