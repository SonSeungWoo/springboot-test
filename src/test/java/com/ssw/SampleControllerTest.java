package com.ssw;

import com.ssw.controller.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class SampleControllerTest {

    /**
     * innerClass  일때 static
     *
     * @TestConfiguration -> @Configuration 변경하면 TestConfig가 main이 된다.
     */
    //@TestConfiguration
    @Configuration
    @ComponentScan(basePackageClasses = Application.class)
    static class TestConfig {

        @Bean
        public String testBean() {
            return "testBean";
        }
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    String testBean;

    @Autowired
    String myBean;

    @Autowired
    SampleController sampleController;

    @LocalServerPort
    int port;

    @Test
    public void testPerson() throws Exception {
        System.out.println("===================== port ======================");
        System.out.println(port);
        System.out.println("=================================================");

        assertThat(testBean).isNotNull();
        assertThat(mockMvc).isNotNull();
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().string("hello"));
        //assertThat("hello").isEqualTo("hello");
    }
}
