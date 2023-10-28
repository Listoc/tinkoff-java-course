package edu.hw2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import edu.hw2.task4.*;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void someTest() {
        var expected = new CallingInfo(Task4Test.class.getName(), "someTest");
        assertThat(Task4.callingInfo()).isEqualTo(expected);
    }

    @Test
    void anotherTest() {
        var expected = new CallingInfo(Task4Test.class.getName(), "anotherTest");
        assertThat(Task4.callingInfo()).isEqualTo(expected);
    }
}
