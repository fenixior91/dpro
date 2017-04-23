package com.dpro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import static com.dpro.utils.Roles.*;

/**
 * Jakie ma zostać wykonane żądanie po pomyślym zalogowaniu się do systemu.
 *
 * @author Tomasz Truszkowski
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();

        authorities.forEach((authority) -> {
            roles.add(authority.getAuthority());
        });

        if (isAdmin(roles)) {
            url = "/admin";
        } else if (isInstructor(roles)) {
            url = "/instructor";
        } else if (isStudent(roles)) {
            url = "/student";
        }

        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains(ADMIN_ROLE);
    }

    private boolean isInstructor(List<String> roles) {
        return roles.contains(INSTRUCTOR_ROLE);
    }

    private boolean isStudent(List<String> roles) {
        return roles.contains(STUDENT_ROLE);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
