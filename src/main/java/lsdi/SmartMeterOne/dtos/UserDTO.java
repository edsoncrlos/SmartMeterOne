package lsdi.SmartMeterOne.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDTO {
    public UserDTO(String fullName, List<String> permissionList) {
        this.fullName = fullName;
        this.permissionList = permissionList;
    }
    @JsonProperty("full_name")
    String fullName;

    @JsonProperty("permission_list")
    List<String> permissionList;

}
