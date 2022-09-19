package com.example.washer.repository;

import com.example.washer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {
}
