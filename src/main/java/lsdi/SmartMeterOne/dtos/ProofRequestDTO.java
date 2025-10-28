package lsdi.SmartMeterOne.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ProofRequestDTO {
    private String connectionId;
    private Map<String, Object> presentationRequest;
}
