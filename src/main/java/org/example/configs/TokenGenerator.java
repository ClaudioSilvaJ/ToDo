package org.example.configs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.dtos.TokenDTO;

import java.util.Date;

public class TokenGenerator {
        public TokenDTO generatorToken(String email){
                long nowMillis = System.currentTimeMillis();
                Date now =  new Date(nowMillis);
                Date expiration = new Date(nowMillis + 360000);

                final String jwtToken = Jwts.builder()
                        .setSubject(email)
                        .setIssuedAt(now)
                        .setExpiration(expiration)
                        .signWith(SignatureAlgorithm.HS256, email)
                        .compact();

                return new TokenDTO(jwtToken, now, expiration, email);
    }
}
