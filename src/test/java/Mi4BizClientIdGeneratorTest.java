import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class Mi4BizClientIdGeneratorTest {

    @Test
    public void it_should_generate_mi4biz_client_id() {
        //given
        Mi4BizClientIdGenerator generator = new Mi4BizClientIdGenerator();
        Mi4BizClientModel model = prepareSampleModel();

        //when
        String clientId = generator.generateClientId(model);

        //then
        assertThat(clientId)
                .isEqualTo("VrEc9vQAsla%2FXyEpA9QYoref1xJxltE3MWKrgyTIXMRrq9N5Ipdvr6M6OvKgtYmqxWqQ5BUfpuOxqrmHYH4pTw%3D%3D");
    }

    private Mi4BizClientModel prepareSampleModel() {
        byte[] sampleInitialVector = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        };

        Mi4BizClientModel model = new Mi4BizClientModel();
        model.setClientIdPlain("plain-text");
        model.setNonce("nonce");
        model.setPassword("password");
        model.setInitialVector(sampleInitialVector);
        return model;
    }
}