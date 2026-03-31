package jay.six.CadastroDeUsuarios.user.dto;

import java.util.UUID;

public record UserMinDTO(

        UUID uuid,
        String name,
        String photo
) {
}
