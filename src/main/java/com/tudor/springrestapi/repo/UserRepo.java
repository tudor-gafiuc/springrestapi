package com.tudor.springrestapi.repo;

import com.tudor.springrestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
