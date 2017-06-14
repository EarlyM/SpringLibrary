package ua.library.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.UserAccountDAO;
import ua.library.model.entities.Account;


@Repository
@Transactional
public class UserAccountDAOImpl extends GenericDAOImpl<Account, String> implements UserAccountDAO {

    @Override
    public Account find(String enquiry) {
        return (Account) getSession().createCriteria(Account.class).add(Restrictions.eq("username", enquiry)).uniqueResult();
    }

    @Override
    public Account findByEmail(String email) {
        return (Account) getSession().createCriteria(Account.class).add(Restrictions.eq("email", email)).uniqueResult();
    }
}
