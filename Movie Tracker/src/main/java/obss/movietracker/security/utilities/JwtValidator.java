package obss.movietracker.security.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import obss.movietracker.security.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "hakkisecret";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setPassword((String) body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return jwtUser;
    }
}

