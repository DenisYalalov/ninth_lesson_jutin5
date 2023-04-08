package junit_lesson_ninth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Простые демонстрационные тесты")
public class SimpleTest {

    @Disabled("Тикет в Jira на заведенную задачу")
    @Test
    @DisplayName("Проверка корректности работы метода methodEx")
    void simpleTest() {
        String actual = methodEx();
        Assertions.assertEquals("1", actual, "");
    }


    String methodEx() {
        return "1";

    }

}
