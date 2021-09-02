package ru.dorogin.bankemulation.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.dorogin.bankemulation.entities.User;


public interface UserService extends UserDetailsService {
    User findUserByUsername(String name);
}
