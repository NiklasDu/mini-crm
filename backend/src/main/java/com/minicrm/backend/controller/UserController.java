package com.minicrm.backend.controller;

import com.minicrm.backend.model.User;
import com.minicrm.backend.model.Task;
import com.minicrm.backend.repository.UserRepository;
import com.minicrm.backend.repository.TaskRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    // --- GET Endpunkte ---
    @GetMapping
    public List<User> getAllUser() {
        return  userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Nutzer mit der ID " + id + " wurde nicht gefunden"));
    }

    // --- POST Endpunkte ---
    @PostMapping
    public User creatUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}/tasks")
    public User addTaskToUser(@PathVariable Long id, @RequestBody Task task) {
        return userRepository.findById(id).map(user -> {
            task.setUser(user);
            taskRepository.save(task);
            return user;
        }).orElseThrow(() -> new RuntimeException("Nutzer mit ID " + id + " nicht gefunden"));
    }
}
