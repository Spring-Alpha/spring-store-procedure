package com.example.springstoreprocedure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class User {
    @Id
    private int id;
    private String name, email;
}

@Repository
interface UserRepository extends CrudRepository<User, Integer> {

    @Procedure(procedureName = "getUserById")
    User getUserById(int userId);
}

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }
}

@RestController
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUserById(@RequestParam int id) {
        return userService.getUserById(id);
    }
}
