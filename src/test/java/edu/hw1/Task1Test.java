package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task1.minuteToSeconds;

public class Task1Test {
    @Test
    @DisplayName("Null на входе")
    void nullTest() {
        assertThat(minuteToSeconds(null)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Правильный ввод mm:ss")
    void properInput() {
        assertThat(minuteToSeconds("20:30")).isEqualTo(1230);
        assertThat(minuteToSeconds("120:59")).isEqualTo(7259);
        assertThat(minuteToSeconds("00:01")).isEqualTo(1);
    }

    @Test
    @DisplayName("60 секунд на входе")
    void tooMuchSeconds() {
        assertThat(minuteToSeconds("10:60")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректные строки")
    void wrongStrings() {
        assertThat(minuteToSeconds("abracadabra")).isEqualTo(-1);
        assertThat(minuteToSeconds("20:20:20")).isEqualTo(-1);
        assertThat(minuteToSeconds("-10:-10")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Недостаточно знаков")
    void tooLowDigits() {
        assertThat(minuteToSeconds("1:1")).isEqualTo(-1);
        assertThat(minuteToSeconds("01:1")).isEqualTo(-1);
        assertThat(minuteToSeconds("1:01")).isEqualTo(-1);
    }

}
