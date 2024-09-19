package pl.wp.gameofthroneapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String role;
    @Transient
    private String confirmedPassword;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomUser that)) return false;

        return getId() == that.getId() && getEmail().equals(that.getEmail()) && getPassword().equals(that.getPassword()) && getConfirmedPassword().equals(that.getConfirmedPassword()) && getRole().equals(that.getRole());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getId());
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        //result = 31 * result + getConfirmedPassword().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }
}