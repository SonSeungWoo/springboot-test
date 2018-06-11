package com.ssw;

import com.ssw.service.RemoteService;
import com.ssw.service.RemoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebFluxTest
@AutoConfigureWebTestClient
public class WebTestClientTest {

    //maven webflux 추가
    @Autowired
    private WebTestClient webTestClient;

    //@MockBean은 객체 새로 만들어서 등록 @SpyBean은 기존 객체를 맵핑한다.
    @SpyBean
    private RemoteService remoteService;

    @Test
    public void testWithWebTestClient() {
        given(this.remoteService.getName()).willReturn("mock");
        webTestClient.get().uri("/person").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("mock");
    }

}
