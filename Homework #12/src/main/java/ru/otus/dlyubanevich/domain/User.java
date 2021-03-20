package ru.otus.dlyubanevich.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String login;
    private String password;
    private String role;

}
