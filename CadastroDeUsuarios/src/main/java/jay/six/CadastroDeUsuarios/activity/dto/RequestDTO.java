package jay.six.CadastroDeUsuarios.activity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestDTO(

        @NotBlank(message = "O título é obrigatório")
        String title,

        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @NotNull(message = "A data é obrigatória")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,

        @NotBlank(message = "O status é obrigatório")
        String status
){}