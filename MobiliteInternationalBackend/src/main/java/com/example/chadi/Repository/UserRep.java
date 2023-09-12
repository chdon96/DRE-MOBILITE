package com.example.chadi.Repository;

import com.example.chadi.Entity.Role;
import com.example.chadi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRep extends JpaRepository<User, Integer> {
     /*@Modifying
    @Query("update User u set u.nom = ?2, u.prenom = ?3, u.email = ?4, u.pass = ?5, u.roles = ?6, u.phone = ?7 where u.idUser = ?1")
    void setUserInfoById(Integer IdUser, String nom, String prenom, String email, String pass, List<RoleTab> roles , Integer phone);*/
    //35:57
    Optional<User> findByEmail(String email);


    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.idUser = ?1")
    int enable(Integer id);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = FALSE WHERE a.idUser = ?1")
    int disable(Integer id);

    @Query("SELECT user FROM User user   WHERE  user.enabled = true ")
    List<User>  TheEnabledOnes();

    @Query("SELECT user FROM User user   WHERE  user.enabled = false ")
    List<User>  TheNonEnabledOnes();

    @Query("SELECT user FROM User user   WHERE  user.role = ?1")
    List<User> findUserByRole(Role role);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
