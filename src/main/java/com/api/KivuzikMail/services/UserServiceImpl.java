package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.KivuzikUser;
import com.api.KivuzikMail.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public KivuzikUser add(KivuzikUser kivuzikUser) {
        return userRepository.save(kivuzikUser);
    }

    @Override
    public Iterable<KivuzikUser> save(List<KivuzikUser> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Collection<KivuzikUser> getAll() {
        return userRepository.getSample();
    }

    @Override
    public KivuzikUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
