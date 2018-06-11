package com.ssw;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


/**
 *
 * @TestConfiguration는 스캔 대상이 아님
 * innerClass일 경우 자동으로 만들어줌
 * 명시적으로 쓰기 위해선 @Import(TestConfig.class) 이런식으로 해서 사용 -> SampleControllerTest
 *
 */
@TestConfiguration
public class TestConfig {

    @Bean
    public String myBean() {
        return "myBean";
    }
}
