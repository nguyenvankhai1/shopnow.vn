package com.xdevs.shopnow.enduser.utils;

import com.xdevs.shopnow.enduser.sys.entities.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String SECRET = "xxxxxxxccccccccccccccccccccccccccccccccccccccccccccccxxxxxxxxxxxxxxxxxxx";
    private final String ISSUER = "XDEVS";
    private final int EXPIRATION_TIME = 24;

    public String generateToken(Customer userDetails) {
        return generateToken(userDetails, EXPIRATION_TIME);
    }

    public String generateToken(Customer userDetails, int expire_time) {
        final Calendar c = Calendar.getInstance();
        final Date currentDate = c.getTime();
        c.add(Calendar.HOUR, expire_time);

        final Date expirationDate = c.getTime();
        final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return Jwts.
                builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuer(ISSUER)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .claim("username", userDetails.getUserName())
                .claim("id", userDetails.getId())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return (Long) Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get("id");
    }

    public String getUserNameFromJWT(String token) {
        final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return (String) Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get("username");
    }

    public boolean validateToken(String authToken) {
        final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
        return true;
    }
}
