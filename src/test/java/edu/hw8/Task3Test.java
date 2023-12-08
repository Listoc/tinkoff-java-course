package edu.hw8;

import java.util.HashMap;
import edu.hw8.task2.Fibonacci;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task3.MultiThreadGuesser;
import edu.hw8.task3.OneThreadGuesser;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task3Test {
    @Test
    void oneThreadTest() {
        var map = new HashMap<String, String>();
        var expected = new HashMap<String, String>();
        var guesser = new OneThreadGuesser();
        map.put("81dc9bdb52d04dc20036dbd8313ed055", "a.v.petrov");
        map.put("1a1dc91c907325c69271ddf0c944bc72", "v.v.belov");
        map.put("c60891094396fe527aba1fa2a49dcec2", "a.s.ivanov");
        map.put("569ef72642be0fadd711d6a468d68ee1", "i.r.bel");
        map.put("d077f244def8a70e5ea758bd8352fcd8", "a.d.petch");

        expected.put("i.r.bel", "te");
        expected.put("a.v.petrov", "1234");
        expected.put("v.v.belov", "pass");
        expected.put("a.s.ivanov", "pa12");
        expected.put("a.d.petch", "cat");

        assertThat(guesser.guessPassword(map)).isEqualTo(expected);
    }

    @Test
    void sixThreadsTest() {
        var map = new HashMap<String, String>();
        var expected = new HashMap<String, String>();
        var guesser = new MultiThreadGuesser(6);
        map.put("81dc9bdb52d04dc20036dbd8313ed055", "a.v.petrov");
        map.put("1a1dc91c907325c69271ddf0c944bc72", "v.v.belov");
        map.put("c60891094396fe527aba1fa2a49dcec2", "a.s.ivanov");
        map.put("569ef72642be0fadd711d6a468d68ee1", "i.r.bel");
        map.put("d077f244def8a70e5ea758bd8352fcd8", "a.d.petch");

        expected.put("i.r.bel", "te");
        expected.put("a.v.petrov", "1234");
        expected.put("v.v.belov", "pass");
        expected.put("a.s.ivanov", "pa12");
        expected.put("a.d.petch", "cat");

        assertThat(guesser.guessPassword(map)).isEqualTo(expected);
    }
}
