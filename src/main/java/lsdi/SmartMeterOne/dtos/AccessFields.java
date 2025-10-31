package lsdi.SmartMeterOne.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class AccessFields {
    public AccessFields(List<String> permissionList, String fullName) {
        this.permissionList = permissionList;
        this.fullName = fullName;
    }

    @JsonProperty("permission_list")
    List<String> permissionList;

    @JsonProperty("full_name")
    String fullName;
}
