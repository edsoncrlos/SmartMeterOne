package lsdi.SmartMeterOne.services;

import lsdi.SmartMeterOne.configs.JwtProperties;
import lsdi.SmartMeterOne.dtos.UserPrincipalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static lsdi.SmartMeterOne.common.JwtConstants.JWT_SECRET;
import static lsdi.SmartMeterOne.common.JwtConstants.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {
    @InjectMocks
    private JwtService jwtService;

    @Mock
    private JwtProperties jwtProperties;

    @BeforeEach
    void setup() {
        when(jwtProperties.secret()).thenReturn(JWT_SECRET);
    }

    @Test
    public void generateToken_ReturnsValidToken() {
        String token = jwtService.generateToken(USER);

        String compactToken = token.replace("Bearer ", "");
        UserPrincipalDTO userParsed = jwtService.parseToken(compactToken);

        System.out.printf(userParsed.fullName());
        System.out.printf(String.valueOf(userParsed.permissionList()));
//        assertThat(userParsed.identifier()).isEqualTo(USER.identifier());
//        assertThat(userParsed.signature()).isEqualTo(USER.signature());
    }

}
