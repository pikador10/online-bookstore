package com.hw.bookstore.domain.repository;

import com.hw.bookstore.domain.entity.Role;
import com.hw.bookstore.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleName role);
}
