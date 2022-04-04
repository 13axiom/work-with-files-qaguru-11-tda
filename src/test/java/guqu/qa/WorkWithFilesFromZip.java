package guqu.qa;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    @Test
    void jsonCommonTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/simple.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            assertThat(jsonObject.get("name").getAsString()).isEqualTo("Dmitrii");
            assertThat(jsonObject.get("address").getAsJsonObject().get("street").getAsString()).isEqualTo("Mira");
        }
    }

    @Test
    void jsonTypeTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/simple.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            Teacher jsonObject = gson.fromJson(json, Teacher.class);
            assertThat(jsonObject.name).isEqualTo("Dmitrii");
            assertThat(jsonObject.address.street).isEqualTo("Mira");
        }
    }

}
