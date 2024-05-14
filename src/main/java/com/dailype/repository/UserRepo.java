package com.dailype.repository;

import com.dailype.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findById(int id);
//    List<User> findAllById(List<UUID>userIds);

    List<User> findByManagerId(UUID managerId);
    List<User> findByMobNum(String mobNum);

}
