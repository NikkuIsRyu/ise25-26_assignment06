package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserDataService;
import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDataService userDataService;

    @Override
    public List<User> getAll() {
        return userDataService.getAll();
    }

    @Override
    public User getById(Long id) {
        return userDataService.getById(id);
    }

    @Override
    public User create(User user) {
        // ensure id is null for creation
        return userDataService.upsert(user);
    }

    @Override
    public User update(Long id, User user) {
        // set id on the domain object (builder)
        User updated = user.toBuilder().id(id).build();
        return userDataService.upsert(updated);
    }

    @Override
    public void delete(Long id) {
        userDataService.delete(id);
    }
}
