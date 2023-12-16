package edu.hw10.task1;

public record TestRecord(@NotNull Integer a1, @Max(100) @Min(50) int a2, boolean a3, char a4, @NotNull InnerTest a5) {}
