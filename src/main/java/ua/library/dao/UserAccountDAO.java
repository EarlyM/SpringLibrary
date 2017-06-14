package ua.library.dao;

import ua.library.model.entities.Account;

public interface UserAccountDAO extends GenericDAO<Account, String> {

    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    Account findByEmail(String email);

}
