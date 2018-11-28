package ch.bbw.zork;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class CommandTests {

    @Test
    public void testCommandGetters() {
        Command command = new Command("test", "argument");
        assertEquals("test", command.getCommandWord());
        assertEquals("argument", command.getSecondWord());
        assertFalse(command.isUnknown());
        assertTrue(command.hasSecondWord());

        command = new Command(null, null);
        assertTrue(command.isUnknown());
        assertFalse(command.hasSecondWord());
    }

    @Test
    public void testParseValidCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("go test".getBytes());
        Parser parser = new Parser(in);
        Command cmd = parser.getCommand();

        assertFalse(cmd.isUnknown());
        assertTrue(cmd.hasSecondWord());
        assertEquals("go", cmd.getCommandWord());
        assertEquals("test", cmd.getSecondWord());
    }

    @Test
    public void testParseInvalidCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("test hi".getBytes());
        Parser parser = new Parser(in);
        Command cmd = parser.getCommand();

        assertTrue(cmd.isUnknown());
        assertNull(cmd.getCommandWord());
    }

    @Test
    public void testParseSimpleCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("help".getBytes());
        Parser parser = new Parser(in);
        Command cmd = parser.getCommand();

        assertFalse(cmd.isUnknown());
        assertFalse(cmd.hasSecondWord());
        assertEquals("help", cmd.getCommandWord());
        assertNull(cmd.getSecondWord());
    }
}
