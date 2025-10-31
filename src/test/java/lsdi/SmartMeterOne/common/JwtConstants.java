package lsdi.SmartMeterOne.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lsdi.SmartMeterOne.dtos.AccessFields;

import java.time.Instant;
import java.util.Date;
import java.util.List;


public class JwtConstants {
    public static final String JWT_SECRET = "my-super-secrect-key-of-32-bytes";


    public static final AccessFields USER = new AccessFields("Danilo Maia", List.of("OneSmartMeterMicrosservice"));
}
