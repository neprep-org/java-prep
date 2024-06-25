package com.rca.myspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rca.myspringsecurity.entity.UserData;
import java.util.Optional;
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    Optional<UserData> findByEmail(String email);
}
