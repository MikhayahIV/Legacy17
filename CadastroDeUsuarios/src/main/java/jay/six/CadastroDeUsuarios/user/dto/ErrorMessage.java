package jay.six.CadastroDeUsuarios.user.dto;

public record ErrorMessage(
        int status,
        String error,
        String message

        ){}