package ru.mirea.onlineclothesstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mirea.onlineclothesstore.model.User;
import ru.mirea.onlineclothesstore.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}