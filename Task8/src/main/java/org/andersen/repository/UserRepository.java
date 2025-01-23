package org.andersen.repository;

import org.andersen.model.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query(value="UPDATE User u SET u.status = 'ACTIVATED' WHERE u.id = :id")
    void updateUserStatus(@Param("id") Long id);
}
