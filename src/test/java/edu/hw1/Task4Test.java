package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task4.fixString;

public class Task4Test {

    @Test
    @DisplayName("Нормальный ввод")
    void properInput() {
        assertThat(fixString("123456")).isEqualTo("214365");
        assertThat(fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
        assertThat(fixString("badce")).isEqualTo("abcde");
        assertThat(fixString("ab")).isEqualTo("ba");
    }

    @Test
    @DisplayName("Null на входе")
    void nullInput() {
        assertThat(fixString(null)).isEqualTo(null);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        assertThat(fixString("")).isEqualTo("");
    }

    @Test
    @DisplayName("Один символ")
    void oneLengthString() {
        assertThat(fixString("a")).isEqualTo("a");
    }
}
