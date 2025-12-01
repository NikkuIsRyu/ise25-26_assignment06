package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.api.mapper.UserDtoMapper;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
//@RequiredArgsConstructor
public class UserController {
    //TODO: Implement user controller
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable Long id){
        User user = userService.getById(id);
        return UserDtoMapper.toDto(user);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
        User domainUser = UserDtoMapper.toDomain(userDto);
        User created = userService.create(domainUser);
        return UserDtoMapper.toDto(created);
    }

    @PutMapping
    public UserDto updateUser(
            @PathVariable Long id, @Valid @RequestBody UserDto userDto){
        User domainUser = UserDtoMapper.toDomain(userDto);
        User updated = userService.update(id, domainUser);
        return UserDtoMapper.toDto(updated);
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

}
