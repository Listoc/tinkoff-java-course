package edu.hw3;

import edu.hw3.task1.Task1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @CsvSource({"Hello world!, Svool dliow!",
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
                "aBzzz.._-!, zYaaa.._-!"
    })
    void properInput(String input, String expected) {

        assertThat(Task1.atbash(input)).isEqualTo(expected);
    }

    @Test
    void nullInput() {
        assertThat(Task1.atbash(null)).isEqualTo(null);
    }

    @Test
    void emptyStringInput() {
        assertThat(Task1.atbash("")).isEqualTo("");
    }
}
