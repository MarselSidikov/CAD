package ru.itis.dao;

import ru.itis.model.User;

/**
 * 25.01.17
 * UsersDao
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersDao {
    User findByToken(String token);
}
