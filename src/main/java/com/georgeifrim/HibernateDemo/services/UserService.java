package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {

    private final UserRepo userRepo;

    @Transactional
    public User createUser(User user) {
        User user1 = new User(user.getFirst_name(), user.getLast_name(), user.isActive());
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
        existingUser.setFirst_name(user.getFirst_name());
        existingUser.setLast_name(user.getLast_name());
        existingUser.setUsername(user.getFirst_name() + "." + user.getLast_name());
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
}
