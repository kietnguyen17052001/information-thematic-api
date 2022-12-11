package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u" +
            " LEFT JOIN FETCH u.role r" +
            " LEFT JOIN FETCH u.school s" +
            " WHERE u.username = :username")
    Optional<UserEntity> findByUsername(String username);
}
