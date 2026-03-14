package jay.six.CadastroDeUsuarios.activity.repository;

import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, UUID> {
}
