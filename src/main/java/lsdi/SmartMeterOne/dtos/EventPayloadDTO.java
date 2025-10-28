package lsdi.SmartMeterOne.dtos;

import lombok.Data;
import lombok.Getter;

//@Data
//@Getter
public record EventPayloadDTO (
     String state,
     String connectionId,
     String presExId
) {}