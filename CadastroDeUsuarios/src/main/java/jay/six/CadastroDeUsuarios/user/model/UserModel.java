package jay.six.CadastroDeUsuarios.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    private String photo;

    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "users")
    @JsonIgnoreProperties("users")
    private Set<ActivityModel> activity = new HashSet<>();

    @Column(nullable = false)
    private boolean enable = false;

    public UserModel() {
    }

    public UserModel(LocalDate birthDate, String photo, String phone, String password, String email, String name) {
        this.birthDate = birthDate;
        this.photo = photo;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<ActivityModel> getActivity() {
        return activity;
    }

    public void setActivity(Set<ActivityModel> activity) {
        this.activity = activity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getAge(){
        if(this.birthDate ==null) return 0;
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

}
