package net.thumbtack.vacancies.rest.token;

import io.jsonwebtoken.*;
import net.thumbtack.vacancies.config.ConfigService;
import net.thumbtack.vacancies.domain.User;

import java.util.Date;

public class JWTService implements TokenService {
    private static volatile ConfigService configService = ConfigService.getInstance();
    private static final JWTService INSTANCE = new JWTService();


    private JWTService() {
    }

    public static JWTService getInstance() {
        return INSTANCE;
    }

    @Override
    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("userId", user.getId())
                .signWith(SignatureAlgorithm.HS256, configService.getKey())
                .compact();
    }

    @Override
    public int getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(configService.getKey())
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Integer.class);
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(configService.getKey())
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
