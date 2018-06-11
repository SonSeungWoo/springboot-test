package com.ssw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssw.dto.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class SampleJsonTest {

    //@Autowired
    JacksonTester<Person> personJackson;

    //JacksonTester @Autowired 말고 다른방법 속도 빠름
    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void testJson() throws IOException {
        assertThat(personJackson).isNotNull();

        Person person = new Person();
        person.setName("seungwoo");
        person.setNumber(100);
        JsonContent<Person> jsonContent = this.personJackson.write(person);

        assertThat(jsonContent)
                .hasJsonPathStringValue("@.name")
                .extractingJsonPathStringValue("@.name").isEqualTo("seungwoo");

        assertThat(jsonContent)
                .hasJsonPathNumberValue("@.number")
                .extractingJsonPathNumberValue("@.number").isEqualTo(100);

    }
}
