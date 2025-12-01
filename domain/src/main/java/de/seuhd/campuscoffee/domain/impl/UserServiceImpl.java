package de.seuhd.campuscoffee.domain.impl;

//import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getbyId(Long id) {
        return null;
    }

    @Override
    public UserDto create(UserDto userdto) {
        return null;
    }

    @Override
    public UserDto update(Long id, UserDto userdto) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
    // TODO: Implement user service
}
