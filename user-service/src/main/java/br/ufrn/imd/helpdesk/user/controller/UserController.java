package br.ufrn.imd.helpdesk.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.helpdesk.user.dto.UserRequestDTO;
import br.ufrn.imd.helpdesk.user.dto.UserResponseDTO;
import br.ufrn.imd.helpdesk.user.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    // Aqui entra a ideia de injeção de dependência    
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return userService.getUserEntity(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest ){
        return userService.updateUser(id,userRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
