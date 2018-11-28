package ch.bbw.zork;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandWordsTests {

    @Test
    public void testCommandValidity() {
        CommandWords words = new CommandWords();
        assertTrue(words.isCommand("go"));
        assertTrue(words.isCommand("backpack"));
        assertFalse(words.isCommand("test"));
        assertFalse(words.isCommand("info"));
    }
}
