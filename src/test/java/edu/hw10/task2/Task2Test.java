package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    void persistTest() throws IOException {
        var fact = new MyFactorial();
        var proxy = (Factorial) CacheProxy.create(fact);
        proxy.persistFactorial(10);
        proxy.persistFactorial(5);
        proxy.persistFactorial(5);
        proxy.noPersistFactorial(7);

        long filesCount;

        try (
            var list = Files.list(Files.createTempDirectory("abv").getParent())
            ) {
            try (var list2 = Files.list(list.filter((path -> path.getFileName().toString().startsWith("cacheDir"))).findAny().get())) {
                filesCount = list2.count();
            }
        }

        assertThat(filesCount).isEqualTo(2);
    }

    @Test
    void factTest() {
        var fact = new MyFactorial();
        var proxy = (Factorial) CacheProxy.create(fact);

        proxy.persistFactorial(5);

        assertThat(proxy.noPersistFactorial(5)).isEqualTo(120);
    }
}
