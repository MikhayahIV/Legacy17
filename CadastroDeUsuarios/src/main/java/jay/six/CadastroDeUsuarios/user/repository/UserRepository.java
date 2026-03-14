package jay.six.CadastroDeUsuarios.user.repository;

import jay.six.CadastroDeUsuarios.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
