package com.gbrramos.lix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.gbrramos.lix.models.User;

public interface IUserRepository extends JpaRepository<User, Long>{

    List<User> findByEmail(String email);

}
