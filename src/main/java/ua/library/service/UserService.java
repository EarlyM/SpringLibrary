package ua.library.service;

import ua.library.model.UserDto;
import ua.library.model.entities.Account;

public interface UserService {
    Account registrationUser(UserDto userDto);
}
