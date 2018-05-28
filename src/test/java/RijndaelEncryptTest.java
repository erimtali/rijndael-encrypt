import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class RijndaelEncryptTest {

    @Test
    public void it_should_encrypt_given_plain_text() {
        //given
        String plainText = "plain-text";
        byte[] key = DigestUtils.sha256("0123456789".getBytes());
        byte[] INITIAL_VECTOR = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        };

        //when
        byte[] encrypt = RijndaelEncrypt.encrypt(plainText.getBytes(), key, INITIAL_VECTOR);

        //then
        assertThat(Base64.encodeBase64String(encrypt)).isEqualTo("O/rD1+jYi7Ub9650h29QYbRLEdqBvOUeQW+gm5zd2ns=");
    }

    @Test
    public void it_should_throw_exception_when_error_occurred_during_the_process_of_encryption() {
        //given
        String plainText = "plain-text";
        byte[] INITIAL_VECTOR = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        };

        //when
        Throwable throwable = catchThrowable(() -> RijndaelEncrypt.encrypt(plainText.getBytes(), "123".getBytes(), INITIAL_VECTOR));

        //then
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        RuntimeException runtimeException = (RuntimeException) throwable;
        assertThat(runtimeException.getMessage()).isEqualTo("Error occurred when encrypting given data. Base64EncodedData : cGxhaW4tdGV4dA==");
    }
}