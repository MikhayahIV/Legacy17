package jay.six.CadastroDeUsuarios.user.controller;

import jakarta.validation.Valid;
import jay.six.CadastroDeUsuarios.user.dto.UserMinDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserResponseDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserRequestDTO;
import jay.six.CadastroDeUsuarios.user.mapper.UserMapper;
import jay.six.CadastroDeUsuarios.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<?> listUsers(@RequestParam(required = false, defaultValue = "false") boolean simple) {
        if (simple) {
            List<UserMinDTO> usersMin = service.findAllMin();
            return ResponseEntity.ok(usersMin);
        }
        List<UserResponseDTO> usersFull = service.usersList();
        return ResponseEntity.ok(usersFull);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable UUID id, @RequestBody @Valid UserRequestDTO dto){
        return ResponseEntity.ok(service.attUser(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
