package jay.six.CadastroDeUsuarios.activity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jay.six.CadastroDeUsuarios.user.model.UserModel;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ACTIVITIES")
public class ActivityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name ="TB_USERS_ACTIVITIES",joinColumns = @JoinColumn(name = "activity_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("activities")
    private Set<UserModel> users = new HashSet<>();

    public ActivityModel() {
    }

    public ActivityModel(UUID uuid, String title, String description, LocalDate date, String status) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public ActivityModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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