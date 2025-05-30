import com.example.DrawingApp;
import com.example.Canvas;
import com.example.commands.Command;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DrawingAppTests {

    @Test
    public void testDisplayCanvasExecutesAllCommands() {
        DrawingApp app = new DrawingApp();
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);

        // Simulate pushing commands onto the stack
        app.addCommand(command1);
        app.addCommand(command2);

        // Call displayCanvas and verify both commands are executed
        app.displayCanvas();
        verify(command1).execute(any(Canvas.class));
        verify(command2).execute(any(Canvas.class));
    }

    @Test
    public void testRunHandlesQuitCommand() {
        String input = "Q\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DrawingApp.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Exiting the Drawing App. Goodbye!"));
    }

    @Test
    public void testRunHandlesInvalidCommand() {
        String input = "INVALID\nQ\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DrawingApp.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Error: Unknown command"));
    }
}