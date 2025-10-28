package lsdi.SmartMeterOne.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class AccessFieldsDTO {
    private List<String> permissionList;
    private String fullName;
}
