package dieguidev.devtree.persistence.repository;

import dieguidev.devtree.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByHandle(String handle);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.handle = COALESCE(:handle, u.handle), u.description = COALESCE(:description, u.description) WHERE u.id = :id")
    int updateHandleAndDescription(String id, String handle, String description);
}
