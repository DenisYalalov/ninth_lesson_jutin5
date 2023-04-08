package junit_lesson_ninth.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkLessonJunit {

    static ClassLoader cl = HomeWorkLessonJunit.class.getClassLoader();

    @DisplayName("Search requests for GitHub")
    @ParameterizedTest(name = "В выдаче результатов на первом месте должен отображаться {0}")
    @ValueSource(strings = {"GURU", "Junit5"})
    void firstSearchResultShouldContainExpectedTest(String testData) {
        open("https://github.com/");
        $("[placeholder='Search GitHub']").setValue(testData).pressEnter();
        $$("ul.repo-list li").first().$("a").shouldHave(text(testData));

    }

    @DisplayName("Registration check for demoqa.com")
    @ParameterizedTest(name = "В поле вывода результатов регистрации должны отображаться {0}, {1}, {2}, {3}")
    @CsvSource({
            "Denis Yalalov, denis.yalalov@bk.com, Some address 1, Another address 1",
            "Andrey Grigoriev, andrey@grigoriev.com, Some address 3, Another address 4"
    })
    void fillFormTest(String testName, String testEmail, String currentAddress, String permanentAddress) {
        open("https://demoqa.com/text-box");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#userName").setValue(testName);
        $("#userEmail").setValue(testEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldHave(text(testName), text(testEmail),
                text(currentAddress), text(permanentAddress));
    }


    static Stream<Arguments> uploadPicture() throws URISyntaxException {
        File myFile1 = new File(cl.getResource("test1.jpg").toURI());
        File myFile2 = new File(cl.getResource("MA.jpg").toURI());
        return Stream.of(
                Arguments.of(myFile1, "test1.jpg"),
                Arguments.of(myFile2, "MA.jpg")
        );
    }

    @DisplayName("Проверка загрузки файла на demoqa.com")
    @ParameterizedTest(name = "При загрузке файла {0} в пути загруженного файла должно отображаться {1}")

    @MethodSource()
    void uploadPicture(File file, String fileName) {
        open("https://demoqa.com/upload-download");

        $("#uploadFile").uploadFile(file);

        $("#uploadedFilePath").shouldHave(text(fileName));

    }
}






