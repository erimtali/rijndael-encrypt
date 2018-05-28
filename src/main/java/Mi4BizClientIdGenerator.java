import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Mi4BizClientIdGenerator {

    public String generateClientId(Mi4BizClientModel model) {
        try {
            byte[] key = DigestUtils.sha256(model.getPassword());
            byte[] initialVector = model.getInitialVector();
            byte[] plainData = model.getClientIdPlain().getBytes();

            byte[] encryptedClientId = RijndaelEncrypt.encrypt(plainData, key, initialVector);

            byte[] nonce = model.getNonce().getBytes();
            byte[] withNonce = addNonce(nonce, encryptedClientId);

            byte[] encryptedClientIdWithNonce = RijndaelEncrypt.encrypt(withNonce, key, initialVector);

            String encodedBase64ClientId = Base64.encodeBase64String(encryptedClientIdWithNonce);
            return URLEncoder.encode(encodedBase64ClientId, Charset.defaultCharset().name());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while generating client id with clientIdPlain= " + model.getClientIdPlain());
        }
    }

    private byte[] addNonce(byte[] nonce, byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(nonce);
        outputStream.write(data);

        return outputStream.toByteArray();
    }
}
