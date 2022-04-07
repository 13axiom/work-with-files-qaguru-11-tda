package guqu.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class ZoomSelenideTestAndNoBanner {


        @Test
        @DisplayName("from lesson Selenide#2")
    public void zoomSelenideTestAndNoBanner() {
        open("https://demoqa.com/automation-practice-form");
            Selenide.zoom(0.75);
            Configuration.browserSize = "1920x1080";
            executeJavaScript("document.querySelector(\"footer\").hidden = 'true';" +
                    "document.querySelector(\"#fixedban\").hidden = 'true'");
        sleep(5000);
    }
}
