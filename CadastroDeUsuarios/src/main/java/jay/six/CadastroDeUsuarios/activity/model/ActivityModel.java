package jay.six.CadastroDeUsuarios.activity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jay.six.CadastroDeUsuarios.user.model.UserModel;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ACTIVITIES")
public class ActivityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name ="TB_USERS_ACTIVITIES",joinColumns = @JoinColumn(name = "activity_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("activities")
    private Set<UserModel> users = new HashSet<>();

    public ActivityModel() {
    }

    public ActivityModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    public void addUser(UserModel user){
       this.users.add(user);
       user.getActivity().add(this);
    }

    public void removeUsers(UserModel user){
        this.users.remove(user);
        user.getActivity().remove(this);
    }

}