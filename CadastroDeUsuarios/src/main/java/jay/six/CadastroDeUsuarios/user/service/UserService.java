package jay.six.CadastroDeUsuarios.user.service;


import jay.six.CadastroDeUsuarios.user.dto.UserResponseDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserRequestDTO;
import jay.six.CadastroDeUsuarios.user.mapper.UserMapper;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import jay.six.CadastroDeUsuarios.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO dto){
        UserModel user = mapper.toEntity(dto);
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
        if(!repository.existsById(uuid)){
            throw  new RuntimeException("Não é possivel deletar: usuario não encontrado");
        }
        repository.deleteById(uuid);
    }
}
