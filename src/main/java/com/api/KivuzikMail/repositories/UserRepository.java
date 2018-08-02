package com.api.KivuzikMail.repositories;

import com.api.KivuzikMail.models.KivuzikUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<KivuzikUser, Long> {

    KivuzikUser findByEmail(String email);
}
