package guqu.qa;

import com.google.gson.JsonObject;
import guqu.qa.domain.QuestionsFormat;
import io.qameta.allure.internal.shadowed.jackson.core.JsonParser;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithFilesFromZip {

    ClassLoader classLoader = getClass().getClassLoader();


    /*@Test
    void parseFilesInZipTest() throws Exception {
        try
          (InputStream is = classLoader.getResourceAsStream("files/work-with-files-qaguru-11-tda-zip-pdf-csv-xlsx.zip");
                ZipFile zp = new ZipFile(is)) {
            Enumeration<? extends ZipEntry> entry = zp.entries();
            while (entry.hasMoreElements()) {
                ZipEntry ze = entry.nextElement();
                System.out.println("Entry: "+ze.getName());

            }
        }
    }*/
    /*@Test
    void jsonCommonTestByGson() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/questions.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            assertThat(jsonObject.get("quiz").getAsJsonObject().get("maths").getAsJsonObject().get("q1").getAsJsonObject().get("answer").getAsString()).isEqualTo("12");
            //assertThat(jsonObject.get("address").getAsJsonObject().get("street").getAsString()).isEqualTo("Mira");
        }
    }*/

    @Test
    void jsonCommonTestByJackson() throws IOException {
        ObjectMapper om = new ObjectMapper();
        QuestionsFormat json = om.readValue(Paths.get("src/test/resources/files/questions.json").toFile(), QuestionsFormat.class);
        assertThat(json.quiz.maths.q2.answer).isEqualTo("4");
        assertThat(json.quiz.sport.q1.options).contains("New York Bulls");

        }


   /* @Test
    void jsonTypeTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/questions.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            Teacher jsonObject = gson.fromJson(json, Teacher.class);
            assertThat(jsonObject.name).isEqualTo("Dmitrii");
            assertThat(jsonObject.address.street).isEqualTo("Mira");
        }
    }*/

}
