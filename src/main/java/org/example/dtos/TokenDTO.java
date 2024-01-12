package org.example.dtos;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("tokenInfo")
public class TokenDTO {
    @SuppressWarnings("unused")
    @Id
    private String id;
    private String token;
    private Date creationDate;
    private Date expirationDate;
    private String email;

    @SuppressWarnings("unused")
    public TokenDTO(){ }

    public TokenDTO(String token, Date creationDate, Date expirationDate, String email){
        this.token = token;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.email = email;
    }

    public String getToken(){
        return token;
    }

    @SuppressWarnings("unused")
    public Date getCreationDate(){
        return creationDate;
    }

    @SuppressWarnings("unused")
    public Date getExpirationDate(){
        return expirationDate;
    }

    public Object getEmail() {
        return email;
    }
}
