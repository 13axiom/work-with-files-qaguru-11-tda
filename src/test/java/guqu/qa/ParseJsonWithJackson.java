package guqu.qa;

import guqu.qa.domain.QuestionsFormat;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJsonWithJackson {
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void jsonCommonTestByJackson() throws IOException {
        ObjectMapper om = new ObjectMapper();
        try (InputStream is = classLoader.getResourceAsStream("files/questions.json")) {
            QuestionsFormat json = om.readValue(is, QuestionsFormat.class);
            assertThat(json.quiz.maths.q2.answer).isEqualTo("4");
            assertThat(json.quiz.sport.q1.options).contains("New York Bulls");
        }
    }

}
