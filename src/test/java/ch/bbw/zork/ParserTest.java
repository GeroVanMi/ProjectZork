package ch.bbw.zork;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ParserTest {

    Parser parser;
    @Before
    public void setup(){

    }

    @Test
    public void test(){
        String testString="go West";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));

        parser = new Parser(stream);
        Command command = parser.getCommand();
        System.out.println(command.getCommandWord());
        assertEquals("go",command.getCommandWord());
        assertEquals("West",command.getSecondWord());
    }
    /*
    public void setup() throws IOException {

        in = Mockito.mock(InputStream.class);

    }

    @Test
    public void test() throws IOException {
        InputStream test = new ByteArrayInputStream("Go West".getBytes());
        //System.out.println(test.read());
        when(in.read()).thenReturn(test.read());

        //System.out.println(in.read());
        parser = new Parser(in);
        Command command = parser.getCommand();
        assertEquals("Go",command.getCommandWord());
        assertEquals("West",command.getSecondWord());
    }
*/

}
