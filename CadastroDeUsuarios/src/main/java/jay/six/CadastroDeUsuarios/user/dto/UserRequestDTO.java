package jay.six.CadastroDeUsuarios.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UserRequestDTO(

        @NotBlank
        @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "O nome deve conter apenas letras")
        String name,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 8,  message = "A senha deve conter no minimo 8 caracteres")
        @Pattern(regexp = "^(?=.*[@#$%^&+=!]).*$", message = "a senha deve conter um caracter especial")
        String password,

        @Pattern(regexp = "^[0-9]*$", message = "o telefone deve conter apenas numeros")
        @NotBlank(message = "o telefone nao pode ser vazio")
        String phone,

        String photo,

        @Past(message = "Voce ainda nao nasceu ? ")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate

){}