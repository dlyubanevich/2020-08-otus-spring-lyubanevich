package ru.otus.dlyubanevich.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dlyubanevich.domain.User;

import java.util.Optional;

public interface UserDetailRepository extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);

}
