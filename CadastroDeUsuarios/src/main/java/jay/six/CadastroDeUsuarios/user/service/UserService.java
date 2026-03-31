package jay.six.CadastroDeUsuarios.user.service;


import jakarta.persistence.EntityNotFoundException;
import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;
import jay.six.CadastroDeUsuarios.user.dto.UserMinDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserResponseDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserRequestDTO;
import jay.six.CadastroDeUsuarios.user.mapper.UserMapper;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import jay.six.CadastroDeUsuarios.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO dto){
        UserModel user = mapper.toEntity(dto);
        String passwordHash = passwordEncoder.encode(dto.password());
        user.setPassword(passwordHash);
        UserModel saved = repository.save(user);
        return mapper.toResponse(saved);
    }

    public UserResponseDTO findById(UUID uuid) {
        return repository.findById(uuid)
                .map(mapper::toResponse)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado."));
    }

    public List<UserResponseDTO> usersList(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<UserMinDTO> findAllMin() {
        return repository.findAll().stream()
                .map(mapper::toMinDTO)
                .collect(Collectors.toList());
    }

   @Transactional
    public UserResponseDTO attUser(UUID uuid, UserRequestDTO user){
        UserModel userExist = repository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        userExist.setName(user.name());
        userExist.setEmail(user.email());
        userExist.setPhone(user.phone());
        userExist.setPhoto(user.photo());
        userExist.setBirthDate(user.birthDate());

        UserModel userAtt = repository.save(userExist);
        return mapper.toResponse(userAtt);
    }

   @Transactional
    public void delete(UUID uuid){
       UserModel user = repository.findById(uuid)
               .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
       for (ActivityModel activity : new HashSet<>(user.getActivity())) {
           activity.getUsers().remove(user);
       }
        repository.deleteById(uuid);
    }
}
