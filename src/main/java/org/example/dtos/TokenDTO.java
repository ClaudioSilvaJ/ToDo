package org.example.dtos;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("tokenInfo")
public class TokenDTO {
    @Id
    private String id;
    private String token;
    private Date creationDate;
    private Date expirationDate;

    @SuppressWarnings("Unused")
    public TokenDTO(){

    }

    public TokenDTO(String token, Date creationDate, Date expirationDate){
        this.token = token;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public String getToken(){
        return token;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

}
