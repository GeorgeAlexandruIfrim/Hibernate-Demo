package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameNotExist;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        User user1 = new User(user.getFirstName(), user.getLastName(), user.isActive());
        if(userWithUsernameExists(user1.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        log.info("User created");
        return userRepo.save(user1);
    }

    public User updateUser(int id, User user) {
        if(!userWithIdExists(id)){
            throw new RuntimeException("User with id " + id + " does not exist");
        }
        log.info("User updated");
        User existingUser = userRepo.findById(id).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getFirstName() + "." + user.getLastName());
        existingUser.setActive(user.isActive());
        return userRepo.save(existingUser);
    }


    public User getUserById(int id) {
        return userRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean userWithIdExists(int id){
          return userRepo.existsById(id);
    }
    public boolean userWithUsernameExists(String username){
          return userRepo.existsByUsername(username);
    }

    public void changePass(String username, String newPassword) {
        var user =userRepo.findByUsername(username)
                .orElseThrow(() -> new UserWithUsernameNotExist(username));
        var hashedPass = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPass);
        userRepo.save(user);
    }
}
