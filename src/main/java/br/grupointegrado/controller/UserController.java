package br.grupointegrado.controller;

import br.grupointegrado.dto.UserRequestDTO;
import br.grupointegrado.model.User;
import br.grupointegrado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private UserRequestDTO converterParaDTO(User user) {
        return new UserRequestDTO(user.getIdUser(), user.getNomeUser(), user.getEmailUser(), user.getSenhaUser());
    }

    private User converterParaEntidade(UserRequestDTO userDTO) {
        User user = new User();
        user.setIdUser(userDTO.idUser());
        user.setNomeUser(userDTO.nomeUser());
        user.setEmailUser(userDTO.emailUser());
        user.setSenhaUser(userDTO.senhaUser());
        return user;
    }

    @PostMapping
    public ResponseEntity<UserRequestDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = converterParaEntidade(userDTO);
        userRepository.save(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserRequestDTO>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserRequestDTO> dtos = users.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestDTO> getUserById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserRequestDTO userDTO = converterParaDTO(user);
        return ResponseEntity.ok(userDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserRequestDTO> updateUser(@PathVariable Integer id, @RequestBody UserRequestDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setNomeUser(userDTO.nomeUser());
        user.setEmailUser(userDTO.emailUser());
        user.setSenhaUser(userDTO.senhaUser());

        userRepository.save(user);
        UserRequestDTO updatedUserDTO = converterParaDTO(user);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
