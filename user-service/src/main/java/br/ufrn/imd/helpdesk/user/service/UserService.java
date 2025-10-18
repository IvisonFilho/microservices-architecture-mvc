package br.ufrn.imd.helpdesk.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufrn.imd.helpdesk.user.dto.UserRequestDTO;
import br.ufrn.imd.helpdesk.user.dto.UserResponseDTO;
import br.ufrn.imd.helpdesk.user.repository.UserRepository;
import br.ufrn.imd.helpdesk.user.model.UserEntity;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserResponseDTO createUser(UserRequestDTO userRequest){
        UserEntity user = new UserEntity();

        user.setName(userRequest.name());
        user.setPassword(userRequest.password());
        user.setEmail(userRequest.email());
        user.setAddress(userRequest.address());

        UserEntity saveUser = userRepository.save(user);

        return new UserResponseDTO(saveUser.getId(), saveUser.getName(), saveUser.getEmail());
    }

    public ResponseEntity<UserResponseDTO> getUserEntity(Long id){
        //Aparentemente somente userRepository retorna um Option<objeto> , mas o orElseThrow desencapsula ele.
        UserEntity user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user does not exist"));

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(),user.getName(),user.getEmail());

        return ResponseEntity.ok(userResponseDTO);
    }

    public ResponseEntity<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestUpdate){

        UserEntity user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user does not exist"));
        
        if(!user.getEmail().equals(userRequestUpdate.email()) && userRepository.existsByEmail(userRequestUpdate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already in use");
        }

        user.setName(userRequestUpdate.name());
        user.setEmail(userRequestUpdate.email());
        user.setPassword(userRequestUpdate.password());
        user.setAddress(userRequestUpdate.address());

        UserEntity saveUser = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO(saveUser.getId(),saveUser.getName(),saveUser.getEmail());

        return ResponseEntity.ok(userResponseDTO);
    }

    public ResponseEntity<Void> deleteUser(Long id){

        UserEntity user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User does not exist"));

        if(!user.isActive()){
            throw new RuntimeException("User does not exist");
        }

        user.setActive(false);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
