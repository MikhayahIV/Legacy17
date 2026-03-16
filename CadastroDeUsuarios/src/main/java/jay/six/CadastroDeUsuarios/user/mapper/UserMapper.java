package jay.six.CadastroDeUsuarios.user.mapper;

import jay.six.CadastroDeUsuarios.user.dto.UserRequestDTO;
import jay.six.CadastroDeUsuarios.user.dto.UserResponseDTO;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserModel toEntity(UserRequestDTO dto){
        if(dto == null)
            return null;
        return UserModel.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .enable(false)
                .birthdate(dto.birthDate())
                .phone(dto.phone())
                .photo(dto.photo())
                .build();
    }

    public UserResponseDTO toResponse(UserModel model){
        if(model == null)
            return null;
        return new UserResponseDTO(model.getUuid(),model.getName(), model.getEmail(),model.getPhone(), model.getPhoto(), model.getBirthDate(), model.isEnable());
    }
}
