package model.dbclasses;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "USERS")
public class User extends SpeedBy {

    @NotNull
    @Column(name = "FullName", nullable = false, length = 50)
    private String fullName;

    @NotNull
    @Column(name = "Age", nullable = false)
    private int age;

    @NotNull
    @Column(name = "PhoneNumber", nullable = false, length = 30)
    private String phoneNumber;

    @NotNull
    @Column(name = "Email", nullable = false, length = 30)
    private String email;

    @NotNull
    @Column(name = "Password", nullable = false, length = 20)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Announcement> announcements;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.password == ((User) obj).getPassword());
    }
}
