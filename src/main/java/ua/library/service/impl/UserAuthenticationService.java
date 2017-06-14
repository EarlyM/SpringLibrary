package ua.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.library.dao.UserAccountDAO;
import ua.library.model.entities.Account;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserAccountDAO userAccountDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userAccountDAO.find(username);
        if(user == null){
            throw new UsernameNotFoundException("User " + username + "was not found");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();

        grantList.add(new SimpleGrantedAuthority(user.getUserRole()));

        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
