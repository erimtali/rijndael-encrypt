public class Mi4BizClientModel {

    private String password;
    private String clientIdPlain;
    private String nonce;
    private byte[] initialVector;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientIdPlain() {
        return clientIdPlain;
    }

    public void setClientIdPlain(String clientIdPlain) {
        this.clientIdPlain = clientIdPlain;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public byte[] getInitialVector() {
        return initialVector;
    }

    public void setInitialVector(byte[] initialVector) {
        this.initialVector = initialVector;
    }
}
