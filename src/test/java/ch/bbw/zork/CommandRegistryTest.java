package ch.bbw.zork;

import ch.bbw.zork.commands.CommandHandlerGo;
import ch.bbw.zork.commands.CommandRegistry;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandRegistryTest {

    @Test
    public void testRegisterCommand() {
        CommandRegistry registry = new CommandRegistry();
        registry.registerCommand(new CommandHandlerGo());

        assertTrue(registry.hasCommandHandler("go"));
        assertFalse(registry.hasCommandHandler("gow"));
        assertNotNull(registry.getCommandHandler("go"));
    }
}
