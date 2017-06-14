package ua.library.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.library.dao.UserAccountDAO;
import ua.library.model.UserDto;
import ua.library.model.entities.Account;
import ua.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserAccountDAO userAccountDAO;
    @Override
    public Account registrationUser(UserDto userDto) {
        Account account = null;
        if(isExistsAccount(userDto.getUsername())) return account;
        if(isEmailUsed(userDto.getEmail())) return account;

        account = new Account();
        account.setUsername(userDto.getUsername());
        account.setPassword(userDto.getPassword());
        account.setEmail(userDto.getEmail());
        account.setUserRole(userAccountDAO.USER);
        account.setEnabled(true);

        return account;
    }

    private boolean isExistsAccount(String username){
        return userAccountDAO.find(username) == null;
    }

    private boolean isEmailUsed(String email){
        return userAccountDAO.findByEmail(email) == null;
    }

}
