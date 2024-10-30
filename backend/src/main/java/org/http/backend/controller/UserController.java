package org.http.backend.controller;

import org.http.backend.entity.User;
import org.http.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> one(@RequestParam String googleId) {
        return ResponseEntity.ok().body(userService.find(googleId));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @GetMapping("/data")
    public ResponseEntity<Map<String,String>> loggedInUserData(Authentication authentication) {
        if (authentication == null)
            return ResponseEntity.badRequest().build();
        Map <String, String> data = new HashMap<>();
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        String googleId = oidcUser.getSubject();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();

        data.put("googleId", googleId);
        data.put("email", email);
        data.put("name", name);
        return ResponseEntity.ok(data);
    }

}
