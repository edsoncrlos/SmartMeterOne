package lsdi.SmartMeterOne.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lsdi.SmartMeterOne.configs.JwtProperties;
import lsdi.SmartMeterOne.dtos.UserDTO;
import lsdi.SmartMeterOne.dtos.UserPrincipalDTO;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(UserDTO userUserDTO) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.DAYS);
        Date expirationDate = Date.from(expirationTime);

        Key key = Keys.hmacShaKeyFor(jwtProperties.secret().getBytes());
        String compactTokenString = Jwts.builder()
                .claim("sub", userUserDTO.getFullName())
                .claim("sig", userUserDTO.getPermissionList())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return compactTokenString;
    }

    public UserPrincipalDTO parseToken(String token) {
        byte[] secretBytes = jwtProperties.secret().getBytes();

        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(token);

        String fullName = jwsClaims.getBody().getSubject();

        List<String> permissionList = jwsClaims.getBody().get("sig", List.class);

        return new UserPrincipalDTO(fullName, permissionList);
    }
}
