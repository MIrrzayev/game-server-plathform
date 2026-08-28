package com.huseyn.gameserverplathform.controller;
import com.huseyn.gameserverplathform.dto.UserRequestDTO;
import com.huseyn.gameserverplathform.dto.UserResponseDTO;
import com.huseyn.gameserverplathform.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        return userService.createUser(request);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO request){
        return userService.updateUser(id, request);
    }
    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(Authentication authentication){
        String userName = authentication.getName();
        return userService.getUserByUsername(userName);
    }
}