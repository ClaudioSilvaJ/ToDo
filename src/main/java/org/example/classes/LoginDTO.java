package org.example.classes;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("logindata")
public class LoginDTO {
    @Id
    private String id;
    private String email;
    private String password;
    private byte[] salt;

    @SuppressWarnings("unused")
    public LoginDTO() { }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
    	return password;
    }

    public byte[] getSalt() {
    	return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
