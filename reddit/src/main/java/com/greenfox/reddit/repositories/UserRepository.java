package com.greenfox.reddit.repositories;

import com.greenfox.reddit.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

    User findByNameAndPassword(String name, String password);
}
