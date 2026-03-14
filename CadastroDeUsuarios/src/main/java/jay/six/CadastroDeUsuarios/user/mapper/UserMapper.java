package jay.six.CadastroDeUsuarios.user.mapper;

import jay.six.CadastroDeUsuarios.user.dto.UserRequestDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserResponseDTO;
import jay.six.CadastroDeUsuarios.user.model.UserModel;

public class UserMapper {

    public UserModel toEntity(UserRequestDTO dto){
        return new UserModel(dto.birthDate(),dto.name(),dto.photo(),dto.password(),dto.email(), dto.phone());
    }

    public UserResponseDTO toResponse(UserModel model){
        return new UserResponseDTO(model.getUuid(),model.getName(), model.getEmail(),model.getPhone(), model.getPhoto(), model.getBirthDate(), model.isEnable());
    }
}
