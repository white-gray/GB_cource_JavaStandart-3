package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private static Main main;

    @BeforeAll
    public static void init(){
        main = new Main();
    }


    static Stream<Arguments> arraysForTest() {
        return Stream.of(
                Arguments.of((new int[] { 1,2,3,4,5,6,7,8,9}), (new int[] {5,6,7,8,9})),
                Arguments.of((new int[] { 4,5,6,7,8,9}), (new int[] {5,6,7,8,9})),
                Arguments.of((new int[] { 1,2,3,4,5,6,4,8,9}), (new int[] {8,9})),
                Arguments.of((new int[] { 1,2,3,4,5,6,7,8,9}), (new int[] {5,6,7,8,9})),
                Arguments.of((new int[] { 1,2,3,9,5,4,7,8,9}), (new int[] {7,8,9})),
                Arguments.of((new int[] { 1,2,3,4,5,6,7,8,4}), (new int[] {})),
                Arguments.of((new int[] { 1,2,3,4,4,6,6,6,6}), (new int[] {6,6,6,6})),
                Arguments.of((new int[] { 1,2,3,4,4,6,6,6}), (new int[] {6,6,6,6})),
                Arguments.of((new int[] {}), (new int[] {})),                           // empty -
                Arguments.of((new int[] { 1,2,3,5,7,6,6,6,6}), (new int[] {6,6,6,6})),  // without 4
                Arguments.of((new int[] { 4}), (new int[] {}))                          // only 4
        );
    }

    @ParameterizedTest
    @MethodSource("arraysForTest")
    public void testCutMassive(int [] in, int [] out) {
        Assertions.assertArrayEquals(out, main.cutMassive(in));
    }


}
