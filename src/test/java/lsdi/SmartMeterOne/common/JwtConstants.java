package lsdi.SmartMeterOne.common;

import lsdi.SmartMeterOne.dtos.UserDTO;

import java.util.List;


public class JwtConstants {
    public static final String JWT_SECRET = "my-super-secrect-key-of-32-bytes";


    public static final UserDTO USER = new UserDTO("Danilo Maia", List.of("OneSmartMeterMicrosservice"));
}
