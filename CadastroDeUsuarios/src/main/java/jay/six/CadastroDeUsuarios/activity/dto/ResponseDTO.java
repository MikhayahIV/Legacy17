package jay.six.CadastroDeUsuarios.activity.dto;

import jay.six.CadastroDeUsuarios.user.dto.UserMinDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ResponseDTO(
        UUID uuid,
        String title,
        String description,
        LocalDate date,
        String status,
        Set<UserMinDTO> members
){}