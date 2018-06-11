package com.ssw;

import com.ssw.controller.SampleController;
import com.ssw.service.RemoteService;
import com.ssw.service.RemoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs
public class SampleMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RemoteService remoteService;

    @Test
    public void testMockMvc() throws Exception {
        given(remoteService.getName()).willReturn("Remote Service");

        mockMvc.perform(get("/person"))
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("person"))
                .andExpect(status().isOk())
                .andExpect(content().string("Remote Service"));
                //.andDo(document("/person"));
    }


    /**
     *
     * ex)모든test에 자동으로 메서드 기반으로 doc생성
     *
     */
    @TestConfiguration
    static class ResultHandlerConfiguration {

        @Bean
        public RestDocumentationResultHandler restDocumentation() {
            return MockMvcRestDocumentation.document("{method-name}");
        }

    }
}
