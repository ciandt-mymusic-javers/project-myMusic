package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(String userId) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            log.error("User was not found.");
            throw new UserNotFoundException("User not found");
        }

        return user.get();
    }
}
