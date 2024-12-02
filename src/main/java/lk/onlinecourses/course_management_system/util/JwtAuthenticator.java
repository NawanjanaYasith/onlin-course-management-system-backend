package lk.onlinecourses.course_management_system.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.onlinecourses.course_management_system.entity.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtAuthenticator {
    private final String jwtSecret="JhS4mWYisQr/X6WtwppkP5UHWGfpYlmPbSN6aZ9a1Gc=";

    private final int jwtExpirationMs = 86400000;


    //generate JWT Token
    public String generateJwtToken(User user) {
        return Jwts.builder()
                .subject((user.getUserName()))
                .id(user.getId().toString())
                .claim("role", user.getRole()) // Adds the user role as a custom claim
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Give the actual secret key
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //Validate token ---> verify the token is generated by us
    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parser().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error occurred when validate....");
        }
        return false;
    }

    public void getUserByToken(String token){
        String jwtToken = token.substring("Bearer ".length());
        String id = Jwts.parser().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody().getId();

        //should get user by id
    }
}