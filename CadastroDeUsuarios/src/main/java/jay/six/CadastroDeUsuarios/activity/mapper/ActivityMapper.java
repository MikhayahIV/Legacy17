package jay.six.CadastroDeUsuarios.activity.mapper;

import jay.six.CadastroDeUsuarios.activity.dto.RequestDTO;
import jay.six.CadastroDeUsuarios.activity.dto.ResponseDTO;
import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;
import jay.six.CadastroDeUsuarios.user.dto.UserMinDTO;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ActivityMapper {

    public ResponseDTO toResponseDTO(ActivityModel activity) {
        if (activity == null) return null;

        Set<UserMinDTO> members = activity.getUsers().stream()
                .map(this::toUserMinDTO)
                .collect(Collectors.toSet());

        return new ResponseDTO(
                activity.getUuid(),
                activity.getTitle(),
                activity.getDescription(),
                activity.getDate(),
                activity.getStatus(),
                members
        );
    }

    public ActivityModel toEntity(RequestDTO dto) {
        if (dto == null) return null;

        ActivityModel entity = new ActivityModel();
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setDate(dto.date());
        entity.setStatus(dto.status());
        return entity;
    }


    private UserMinDTO toUserMinDTO(UserModel user) {
        return new UserMinDTO(
                user.getUuid(),
                user.getName(),
                user.getPhoto()
        );
    }
}
