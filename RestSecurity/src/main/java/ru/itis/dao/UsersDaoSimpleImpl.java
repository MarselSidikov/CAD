package ru.itis.dao;

import org.springframework.stereotype.Repository;
import ru.itis.model.User;

/**
 * 25.01.17
 * UsersDaoSimpleImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Repository
public class UsersDaoSimpleImpl implements UsersDao {
    public User findByToken(String token) {
        return null;
    }
}
