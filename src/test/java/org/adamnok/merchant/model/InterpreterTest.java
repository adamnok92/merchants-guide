package org.adamnok.merchant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class InterpreterTest {
    @Autowired
    private Interpreter interpreter;

    @Test
    void test() {
        final var result1 = interpreter.process("glob is I");
        assertEquals(Optional.empty(), result1);

        final var result2 = interpreter.process("prok is V");
        assertEquals(Optional.empty(), result2);

        final var result3 = interpreter.process("pish is X");
        assertEquals(Optional.empty(), result3);

        final var result4 = interpreter.process("tegj is L");
        assertEquals(Optional.empty(), result4);

        final var result5 = interpreter.process("glob glob Silver is 34 Credits");
        assertEquals(Optional.empty(), result5);

        final var result6 = interpreter.process("glob prok Gold is 57800 Credits");
        assertEquals(Optional.empty(), result6);

        final var result7 = interpreter.process("pish pish Iron is 3910 Credits");
        assertEquals(Optional.empty(), result7);

        final var result8 = interpreter.process("how much is pish tegj glob glob ?");
        assertEquals(Optional.of("pish tegj glob glob is 42"), result8);

        final var result9 = interpreter.process("how many Credits is glob prok Silver ?");
        assertEquals(Optional.of("glob prok Silver is 68 Credits"), result9);

        final var result10 = interpreter.process("how many Credits is glob prok Gold ?");
        assertEquals(Optional.of("glob prok Gold is 57800 Credits"), result10);

        final var result11 = interpreter.process("how many Credits is glob prok Iron ?");
        assertEquals(Optional.of("glob prok Iron is 782 Credits"), result11);

        final var result12 = interpreter.process("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        assertEquals(Optional.of("I have no idea what you are talking about"), result12);
    }
}