package com.example.backendspring.service;

import com.example.backendspring.entity.UserEntity;
import com.example.backendspring.exception.UserAlreadyExistsException;
import com.example.backendspring.exception.UserNotFoundException;
import com.example.backendspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw  new UserAlreadyExistsException("A user with that name exists!!!");
        }
        return userRepo.save(user);
    }

    public com.example.backendspring.model.User getOne(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findById(id).get();
        if (userEntity == null) {
            throw new UserNotFoundException("User not found!!!");
        }
        return com.example.backendspring.model.User.toModel(userEntity);
    }

    public ArrayList<UserEntity> getAll() {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        for (var userEntity : userRepo.findAll()) {
            userEntities.add(userEntity);
        }
        return userEntities;
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
