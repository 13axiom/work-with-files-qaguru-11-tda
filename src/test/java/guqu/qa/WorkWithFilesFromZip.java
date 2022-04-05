package guqu.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithFilesFromZip {

    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void parseFilesInZipTest() throws Exception {
        //создаем объект ZipFile (берем зип архив по ссылке)
        ZipFile zp = new ZipFile("src/test/resources/files/work-with-files-qaguru-11-tda-zip-pdf-csv-xlsx.zip");

        //далее получаем перечисление элементов оглавления архива методом entries
        Enumeration<? extends ZipEntry> entry = zp.entries();

        //пользуясь этим перечислением, организовываем извлечение всех элементов оглавления для последующей записи в массив
        while (entry.hasMoreElements()) {
            ZipEntry ze = entry.nextElement();

            //исключаем извлечение служебных файлов MacOs
            if (ze.getName().contains("__MACOSX/.")) {
                continue;
            } else //если это не служебный файл, то производим обработку/проверку файла в зависимости от типа
            {

                //xlsx
                if (ze.getName().contains(".xlsx")) {
                    InputStream is = zp.getInputStream(ze);
                    XLS xlsx = new XLS(is);
                    System.out.println("Entry: " + ze.getName());
                    assertThat(xlsx.name).isEqualTo("");
                    assertThat(xlsx.excel.getSheetAt(1)
                            .getRow(1)
                            .getCell(0)
                            .getStringCellValue()).isEqualTo("Для отрезков до 1200 метров - рассчитывается исходя из вашего времени на 5 километров (авторы предлагают в своих таблицах время для результатов на 5 км от 15:30 до 30:00):");

                    assertThat(xlsx.excel.getSheetAt(1)
                            .getRow(1)
                            .getCell(0)
                            .getStringCellValue()).contains("Для отрезков до 1200 метров");

                }

                //pdf
                if (ze.getName().contains(".pdf")) {
                    InputStream is = zp.getInputStream(ze);
                    PDF pdf = new PDF(is);
                    System.out.println("Entry: " + ze.getName());
                    assertThat(pdf.author).isEqualTo("");
                    assertThat(pdf.numberOfPages).isEqualTo(4);
                    assertThat(pdf.text).contains("Study Guide: Data Retrieval with SQL");
                }

                //csv
                if (ze.getName().contains(".csv")) {
                    try (InputStream is = zp.getInputStream(ze);
                         CSVReader reader = new CSVReader(new InputStreamReader(is))) {
                        List<String[]> content = reader.readAll();
                        System.out.println("Entry: " + ze.getName());
                        assertThat(content.get(0)).contains(
                                "Series_reference",
                                "Period",
                                "Data_value",
                                "Suppressed",
                                "STATUS",
                                "UNITS",
                                "Magnitude",
                                "Subject",
                                "Group",
                                "Series_title_1",
                                "Series_title_2",
                                "Series_title_3",
                                "Series_title_4",
                                "Series_title_5");

                        assertThat(content.get(7)).contains(
                                "36422");
                    }
                }
            }
        }
    }

}



