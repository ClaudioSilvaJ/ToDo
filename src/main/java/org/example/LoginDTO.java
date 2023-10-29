package org.example;

public class LoginDTO {


    public String email;
    public String password;

    @SuppressWarnings("unused")
    public LoginDTO() { }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
