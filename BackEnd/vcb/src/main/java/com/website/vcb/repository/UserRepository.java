package com.website.vcb.repository;

import com.website.vcb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Kiểm tra tài khoảng đã tồn tại hay chưa
    boolean existsByUsername(String username);

    // Truy vấn và lấy ra đối tượng user có  username khớp
    Optional<User> findByUsername(String username);
}
