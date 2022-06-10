package com.hcd.websketch.repository;

import com.hcd.websketch.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(final String username);
}
