package ru.itis.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.security.auth.TokenAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 25.01.17
 * TokenAuthFilter
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class TokenAuthFilter extends GenericFilterBean {
    private static final String header = "Auth-Token";

    private AuthenticationManager authenticationManager;

    /**
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    **/

    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        try {
            String headerValue = httpServletRequest.getHeader(header);

            if (!isSecuredMethod(httpServletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (headerValue == null || headerValue.equals("")) {
                throw new IllegalArgumentException("Token not found");
            } else {
                authenticationManager.authenticate(new TokenAuthentication(headerValue));
            }
        } catch (AuthenticationException authenticationException) {
            // authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
            throw new IllegalArgumentException(authenticationException);
        }
    }

    private boolean isSecuredMethod(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/login");
    }

}
