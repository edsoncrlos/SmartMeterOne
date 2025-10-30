package lsdi.SmartMeterOne.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProofRequesta {
    @JsonProperty("connection_id")
    private String connectionId;

    @JsonProperty("presentation_request")
    private PresentationRequest presentationRequest;

    public ProofRequesta(String connectionId, String issuerDid) {
        Restriction restriction = new Restriction();
        restriction.issuerDid = issuerDid;

        Attr attr = new Attr();
        attr.names = List.of("permission_list", "full_name");
        attr.restrictions = List.of(restriction);

        RequestedAttributes requestedAttributes = new RequestedAttributes();
        requestedAttributes.attr = attr;

        // Indy (nome, versão, atributos e predicados)
        Indy indy = new Indy();
        indy.name= "Application Proof Request";
        indy.version = "1.0";
        indy.requestedAttributes = requestedAttributes;
        indy.requestedPredicates = Map.of();

        // PresentationRequest
        PresentationRequest presentationRequest = new PresentationRequest();
        presentationRequest.indy = indy;

        this.connectionId = connectionId;
        this.presentationRequest = presentationRequest;
    }

    @Data
    public class PresentationRequest {
        private Indy indy;
    }

    @Data
    public class Indy {
        private String name;
        private String version;

        @JsonProperty("requested_attributes")
        private RequestedAttributes requestedAttributes;

        @JsonProperty("requested_predicates")
        private Map<String, Object> requestedPredicates; // vazio no seu caso
    }

    @Data
    public class RequestedAttributes {
        @JsonProperty("attr")
        private Attr attr;
    }

    @Data
    public class Attr {
        private List<String> names;
        private List<Restriction> restrictions;
    }

    @Data
    public class Restriction {
        @JsonProperty("issuer_did")
        private String issuerDid;
    }
}
