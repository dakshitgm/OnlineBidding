package com.dakshit.OnlineBidding.repository;

import com.dakshit.OnlineBidding.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        public User findByEmailId(String email);
        public User findByUserName(String user);
}
