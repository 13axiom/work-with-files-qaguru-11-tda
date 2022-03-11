package guqu.qa;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithFilesFromZip {

    ClassLoader classLoader = getClass().getClassLoader();

    @Test
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
    }
}
