package ru.itis.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.dao.TokensDao;
import ru.itis.dao.UsersDao;
import ru.itis.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 25.01.17
 * UserDetailsServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private TokensDao tokensDao;

    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (tokensDao.isExistToken(token)) {
            User user = usersDao.findByToken(token);
            List<GrantedAuthority> authorities = createGrantedAuthorities();

            return new UserDetailsImpl(user.getLogin(), user.getHashPassword(), authorities);
        } else {
            return null;
        }
    }

    public static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}
