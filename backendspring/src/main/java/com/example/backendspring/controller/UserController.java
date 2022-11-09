package com.example.backendspring.controller;

import com.example.backendspring.entity.UserEntity;
import com.example.backendspring.exception.UserAlreadyExistsException;
import com.example.backendspring.exception.UserNotFoundException;
import com.example.backendspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().body("User saved successfully!");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error! " + exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error!" + exception.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllUser() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error! " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        }  catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error!" + exception.getMessage());
        }
    }

}
