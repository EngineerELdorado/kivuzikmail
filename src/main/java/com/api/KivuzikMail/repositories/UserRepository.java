package com.api.KivuzikMail.repositories;

import com.api.KivuzikMail.models.KivuzikUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<KivuzikUser, Long> {

    KivuzikUser findByEmail(String email);
    @Query(value = "select * from kivuzik_user limit 100", nativeQuery = true)
    Collection<KivuzikUser>getSample();
}
