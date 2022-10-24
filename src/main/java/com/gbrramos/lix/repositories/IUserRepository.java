package com.gbrramos.lix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gbrramos.lix.models.User;

public interface IUserRepository extends CrudRepository<User, Long>{

    @Query("FROM User WHERE email = ?1")
    List<User> findByEmail(String email);

}
