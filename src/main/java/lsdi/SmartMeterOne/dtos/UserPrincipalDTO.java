package lsdi.SmartMeterOne.dtos;

import java.util.List;

public record UserPrincipalDTO(
        String fullName,
        List<String> permissionList
) {
}
