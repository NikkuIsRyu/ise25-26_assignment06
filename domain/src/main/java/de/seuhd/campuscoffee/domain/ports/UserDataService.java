package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.model.User;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface UserDataService {

    /**
     * Clears all user data from the data store.
     * This is typically used for testing or administrative purposes.
     */
    void clear();

    /**
     * Returns all users.
     *
     * @return list of users, never null
     */
    @NonNull List<User> getAll();

    /**
     * Returns a single user by id.
     *
     * @param id id of the user
     * @return user if present
     */
    @NonNull User getById(@NonNull Long id);

    /**
     * Creates or updates a user.
     *
     * @param user the user to create or update; must not be null
     * @return the persisted user entity with updated timestamps and ID as a domain object; never null
     */
    @NonNull User upsert(@NonNull User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete; must not be null
     */
    void delete(@NonNull Long id);
}
