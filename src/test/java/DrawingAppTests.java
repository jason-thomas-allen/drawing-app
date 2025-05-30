import com.example.DrawingApp;
import com.example.Canvas;
import com.example.commands.Command;
import com.example.commands.CommandFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DrawingAppTests {

    private DrawingApp drawingApp;
    private CommandFactory commandFactory;

    @BeforeEach
    public void setUp() {
        commandFactory = new CommandFactory();
        drawingApp = new DrawingApp(new Canvas(), commandFactory);
    }

    @Test
    public void testDisplayCanvasExecutesAllCommands() {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);

        commandFactory.addCommand(command1);
        commandFactory.addCommand(command2);

        // Call displayCanvas and verify both commands are executed
        drawingApp.displayCanvas();
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