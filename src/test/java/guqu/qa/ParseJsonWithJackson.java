package guqu.qa;

import com.google.gson.JsonObject;
import guqu.qa.domain.QuestionsFormat;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJsonWithJackson {
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void jsonCommonTestByJackson() throws IOException {
        ObjectMapper om = new ObjectMapper();
        QuestionsFormat json = om.readValue(Paths.get("src/test/resources/files/questions.json").toFile(), QuestionsFormat.class);
        assertThat(json.quiz.maths.q2.answer).isEqualTo("4");
        assertThat(json.quiz.sport.q1.options).contains("New York Bulls");

        }

    @Test
    void jsonCommonTestByJackson2() throws IOException {
        ObjectMapper om = new ObjectMapper();
        try (InputStream is = classLoader.getResourceAsStream("files/questions.json")) {
           // QuestionsFormat json = om.readValue(is, QuestionsFormat.class);
            // assertThat(json.quiz.maths.q2.answer).isEqualTo("4");
            // assertThat(json.quiz.sport.q1.options).contains("New York Bulls");
            JsonObject json = om.readValue(is,JsonObject.class);
           // assertThat(json.get("quiz").getAsJsonObject().get("maths").getAsJsonObject().get("q1").getAsJsonObject().get("answer").getAsString()).isEqualTo("12");

        }

    }


}
