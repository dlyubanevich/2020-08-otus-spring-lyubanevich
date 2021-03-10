package ru.otus.dlyubanevich.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dlyubanevich.domain.User;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/auth")
    public ResponseEntity authenticateUser(@RequestBody User user){

        Authentication request = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(request);
        return ResponseEntity.ok(user);

    }
}
