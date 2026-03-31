package jay.six.CadastroDeUsuarios.activity.controller;

import jakarta.validation.Valid;
import jay.six.CadastroDeUsuarios.activity.dto.RequestDTO;
import jay.six.CadastroDeUsuarios.activity.dto.ResponseDTO;
import jay.six.CadastroDeUsuarios.activity.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid RequestDTO dto) {
        ResponseDTO response = activityService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable UUID uuid) {
        ResponseDTO response = activityService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> findAll() {
        List<ResponseDTO> response = activityService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{activityId}/members/{userId}")
    public ResponseEntity<Void> addUser(@PathVariable UUID activityId, @PathVariable UUID userId) {
        activityService.addUserToActivity(activityId, userId);
        return ResponseEntity.noContent().build(); // 204 No Content para associações bem-sucedidas
    }

    @DeleteMapping("/{activityId}/members/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable UUID activityId, @PathVariable UUID userId) {
        activityService.removeUserFromActivity(activityId, userId);
        return ResponseEntity.noContent().build();
    }
}
