package com.ssw;

import com.ssw.service.RemoteService;
import com.ssw.service.RemoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.web.client.RestClientTest(RemoteServiceImpl.class)
public class RestClientTest {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void restTest() {
        this.server.expect(requestTo("/person"))
                .andRespond(withSuccess("Hello", MediaType.TEXT_PLAIN));
        String greeting = this.remoteService.callRestService();
        assertThat(greeting).isEqualTo("Hello");
    }

}
