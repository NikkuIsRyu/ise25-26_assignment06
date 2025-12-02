package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.model.User;

import java.util.List;

public interface UserService {
    java.util.List<User> getAll();
    User getById(Long id);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
}
