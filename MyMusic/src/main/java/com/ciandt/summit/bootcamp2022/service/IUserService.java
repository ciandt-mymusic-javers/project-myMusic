package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;

public interface IUserService {

    User findUser(String userId) throws UserNotFoundException;
}
