package jay.six.CadastroDeUsuarios.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(

        UUID uuid,
        String name,
        String email,
        String phone,
        String photo,
        LocalDate birthDate,
        boolean enabled
){}