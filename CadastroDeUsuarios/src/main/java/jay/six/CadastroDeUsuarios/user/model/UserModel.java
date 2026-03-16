package jay.six.CadastroDeUsuarios.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jay.six.CadastroDeUsuarios.activity.model.ActivityModel;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
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

    private UserModel(Builder builder){
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.photo = builder.photo;
        this.birthDate = builder.birthdate;
        this.activity = builder.activity;
        this.enable = builder.enable;
    }

    public static class  Builder{

        private UUID uuid;
        private String name;
        private String email;
        private String password;
        private String phone;
        private String photo;
        private LocalDate birthdate;
        private Set<ActivityModel> activity = new HashSet<>();
        private boolean enable = false;

        public Builder uuid(UUID uuid){
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder photo(String photo){
            this.photo = photo;
            return this;
        }

        public Builder birthdate(LocalDate birthdate){
            this.birthdate = birthdate;
            return this;
        }

        public Builder activity(Set<ActivityModel> activity){
            this.activity = activity;
            return this;
        }

        public Builder enable(boolean enable){
            this.enable = enable;
            return this;
        }

        public UserModel build(){
            return new UserModel(this);
        }

    }

    public static Builder builder(){
        return new Builder();
    }
}
