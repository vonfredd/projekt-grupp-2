package org.http.backend.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.http.backend.entity.User;
import org.http.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    UserRepository userRepository;

    public CustomOAuth2SuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        String googleId = oidcUser.getSubject();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String userData = "{ \"googleId\": \"" + googleId + "\", \"email\": \"" + email + "\", \"fullName\": \"" + name + "\" }";
        response.getWriter().write(userData);
        Optional<User> existingUser = userRepository.findByGoogleId(googleId);

        if (existingUser.isEmpty()) {
            userRepository.save(new User(googleId, email, name));
        }
        response.sendRedirect("http://localhost:5174/redirect?redirected=true");
    }
}
