package com.userms.userms.repository;

import com.userms.userms.entity.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepo extends JpaRepository<UserManagement,Long> {
}
