package ru.itis.dao;

import org.springframework.stereotype.Repository;

/**
 * 25.01.17
 * TokensDaoSimpleImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Repository
public class TokensDaoSimpleImpl implements TokensDao {


    public boolean isExistToken(String token) {
        return false;
    }
}
