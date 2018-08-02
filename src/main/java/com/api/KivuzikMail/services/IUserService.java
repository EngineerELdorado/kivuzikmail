package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.KivuzikUser;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    KivuzikUser add(KivuzikUser kivuzikUser);
    Iterable<KivuzikUser>save(List<KivuzikUser> users);
    Collection<KivuzikUser>getAll();
    KivuzikUser findByEmail(String email);
}
