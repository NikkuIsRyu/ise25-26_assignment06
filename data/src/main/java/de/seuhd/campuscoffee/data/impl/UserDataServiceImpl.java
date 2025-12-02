package de.seuhd.campuscoffee.data.impl;

import de.seuhd.campuscoffee.data.mapper.UserEntityMapper;
import de.seuhd.campuscoffee.data.persistence.UserEntity;
import de.seuhd.campuscoffee.data.persistence.UserRepository;
import de.seuhd.campuscoffee.data.util.ConstraintViolationChecker;
import de.seuhd.campuscoffee.domain.exceptions.DuplicationException;
import de.seuhd.campuscoffee.domain.exceptions.NotFoundException;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserDataService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void clear() {
        userRepository.deleteAll();
        userRepository.resetSequence();
    }

    @Override
    @NonNull
    public List<User> getAll() {
        return userRepository.findAll().stream()
                .map(userEntityMapper::fromEntity)
                .toList();
    }

    @Override
    @NonNull
    public User getById(@NonNull Long id) {
        return userRepository.findById(id)
                .map(userEntityMapper::fromEntity)
                .orElseThrow(() -> new NotFoundException(User.class, id));
    }

    @Override
    @NonNull
    public User upsert(@NonNull User user) {
        try {
            if (user.id() == null) {
                UserEntity entity = userEntityMapper.toEntity(user);
                UserEntity saved = userRepository.save(entity);
                return userEntityMapper.fromEntity(saved);
            } else {
                UserEntity existing = userRepository.findById(user.id())
                        .orElseThrow(() -> new NotFoundException(User.class, user.id()));
                userEntityMapper.updateEntity(user, existing);
                UserEntity saved = userRepository.save(existing);
                return userEntityMapper.fromEntity(saved);
            }
        } catch (DataIntegrityViolationException e) {
            if (ConstraintViolationChecker.isConstraintViolation(e, UserEntity.LOGIN_NAME_CONSTRAINT)) {
                throw new DuplicationException(User.class, "loginName", user.loginName());
            }
            if (ConstraintViolationChecker.isConstraintViolation(e, UserEntity.EMAIL_ADDRESS_CONSTRAINT)) {
                throw new DuplicationException(User.class, "emailAddress", user.emailAddress());
            }
            throw e;
        }
    }

    @Override
    public void delete(@NonNull Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(User.class, id);
        }
        userRepository.deleteById(id);
    }
}
