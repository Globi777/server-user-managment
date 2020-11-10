package com.jdabrowski.serverusermanagment.repository;

import com.jdabrowski.serverusermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndUsername(Long id, String name);

    @Query("Select u.name from User u where u.id in (:pIdList)")
    List<User> findUserIdList(@Param("pIdList") List<Long> idList);
}
