package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.dao.UserDetailRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LibraryUserDetailService implements UserDetailsService {

    private final UserDetailRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var optionalUser = repository.findByLogin(login);
        var user = optionalUser.orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
