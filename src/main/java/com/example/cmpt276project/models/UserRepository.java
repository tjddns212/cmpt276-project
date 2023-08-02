package com.example.cmpt276project.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByUid(int uid);
    List<User> findByEmail(String email);
    List<User> findByNick(String nick);
    List<User> findByFirst(String first);

//    List<User> findAllByOrderByIdAsc();

//    @Query("DELETE FROM User u WHERE u.uid = ?1")
    void deleteById(int uid);
}
