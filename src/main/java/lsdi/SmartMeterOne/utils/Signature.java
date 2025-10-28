package lsdi.SmartMeterOne.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.ECDSASigner;

import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Signature {
    public static String getSignature() {
        try {
            PrivateKey privateKey = loadPrivateKey("ec-private-pkcs8.pem");

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256)
                    .keyID("")
                    .build();

            Payload payload = new Payload("");

            JWSObject jwsObject = new JWSObject(header, payload);

            JWSSigner signer = new ECDSASigner((ECPrivateKey) privateKey);
            jwsObject.sign(signer);

            return jwsObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey loadPrivateKey(String resourcePath) throws Exception {
        try (InputStream is = Signature.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            String pem = new String(is.readAllBytes());

            pem = pem.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] keyBytes = Base64.getDecoder().decode(pem);

            // 5. Convert from PKCS#8 to PrivateKey
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("EC");
            return kf.generatePrivate(keySpec);
        }
    }

}
