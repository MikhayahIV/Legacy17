package jay.six.CadastroDeUsuarios.activity.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jay.six.CadastroDeUsuarios.activity.dto.RequestDTO;
import jay.six.CadastroDeUsuarios.activity.dto.ResponseDTO;
import jay.six.CadastroDeUsuarios.activity.mapper.ActivityMapper;
import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;
import jay.six.CadastroDeUsuarios.activity.repository.ActivityRepository;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import jay.six.CadastroDeUsuarios.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityMapper activityMapper;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.activityMapper = activityMapper;
    }

    @Transactional
    public ResponseDTO create(RequestDTO dto) {
        ActivityModel activity = activityMapper.toEntity(dto);
        ActivityModel savedActivity = activityRepository.save(activity);
        return activityMapper.toResponseDTO(savedActivity);
    }


    public ResponseDTO findById(UUID uuid) {
        ActivityModel activity = activityRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada"));
        return activityMapper.toResponseDTO(activity);
    }

    public List<ResponseDTO> findAll() {
        return activityRepository.findAll().stream()
                .map(activityMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addUserToActivity(UUID activityId, UUID userId) {
        ActivityModel activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada"));
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        activity.addUser(user);
        activityRepository.save(activity);
    }

    @Transactional
    public void removeUserFromActivity(UUID activityId, UUID userId) {
        ActivityModel activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        activity.removeUsers(user);
        activityRepository.save(activity);
    }
}
