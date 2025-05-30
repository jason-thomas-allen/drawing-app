import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

import com.example.Drawable;
import com.example.commands.Command;
import com.example.commands.CommandFactory;
import com.example.commands.InitializeCanvasCommand;
import com.example.commands.DrawLineCommand;
import com.example.commands.DrawRectangleCommand;
import com.example.commands.FillCommand;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTests {
    @Test
    public void testCanParseInitializeCanvasCommand() {
        String input = "C 20 4";
        Command command = CommandFactory.getCommand(input);
        assertTrue(command instanceof InitializeCanvasCommand);
        assertEquals(20, ((InitializeCanvasCommand) command).getWidth());
        assertEquals(4, ((InitializeCanvasCommand) command).getHeight());
    }

    @Test
    public void testInitializeCanvasCommandWithInvalidArgsThrowsException() {
        String input = "C 20";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Invalid initialize canvas command", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "C 1 4",
        "C 20 1"
    })
    public void testCanvasWidthOrHeightLessThanTwoThrowsException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Canvas width and height must be at least 2", exception.getMessage());
    }

    @Test
    public void testInitializeCanvasCommandInitializesCanvasWidthAndHeight() {
        Drawable canvas = mock(Drawable.class);
        String input = "C 30 15";
        Command command = CommandFactory.getCommand(input);
        command.execute(canvas);
        verify(canvas).initialize(30, 15);
    }

    @Test
    public void testInitializeCanvasCommandOnNullCanvasThrowsException() {
        String input = "C 30 15";
        Command command = CommandFactory.getCommand(input);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testCanParseDrawLineCommand() {
        String input = "L 1 2 6 2 x";
        Command command = CommandFactory.getCommand(input);
        assertTrue(command instanceof DrawLineCommand);
        assertEquals(1, ((DrawLineCommand) command).getStartX());
        assertEquals(2, ((DrawLineCommand) command).getStartY());
        assertEquals(6, ((DrawLineCommand) command).getEndX());
        assertEquals(2, ((DrawLineCommand) command).getEndY());
    }

    @Test
    public void testDrawLineCommandWithNonHorizontalOrVerticalLineThrowsException() {
        String input = "L 1 2 3 4 x";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Line must be horizontal or vertical", exception.getMessage());
    }

    @Test
    public void testDrawLineCommandWithInvalidArgsThrowsException() {
        String input = "L 1 2 3 4";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Invalid draw line command", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "L -1 2 6 2 x",
        "L 1 2 -6 2 x",
        "L 1 -2 6 2 x",
        "L 1 2 2 -2 x"
    })
    public void testDrawLineCommandWithNegativeCoordinatesThrowsException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Coordinates must be positive integers", exception.getMessage());
    }

    @Test
    public void testDrawLineCommandDrawsLineOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        String input = "L 1 2 6 2 x";
        Command command = CommandFactory.getCommand(input);
        command.execute(canvas);
        verify(canvas).drawLine(1, 2, 6, 2, 'x');
    }

    @Test
    public void testDrawLineCommandOnNullCanvasThrowsException() {
        String input = "L 1 2 6 2 x";
        Command command = CommandFactory.getCommand(input);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testCanParseDrawRectangleCommand() {
        String input = "R 14 1 18 3 x";
        Command command = CommandFactory.getCommand(input);
        assertTrue(command instanceof DrawRectangleCommand);
        assertEquals(14, ((DrawRectangleCommand) command).getStartX());
        assertEquals(1, ((DrawRectangleCommand) command).getStartY());
        assertEquals(18, ((DrawRectangleCommand) command).getEndX());
        assertEquals(3, ((DrawRectangleCommand) command).getEndY());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "R -1 1 18 3 x",
        "R 14 -1 18 3 x",
        "R 14 1 -18 3 x",
        "R 14 1 18 -3 x"
    })
    public void testDrawRectangleWithNegativeCoordinatesThrowsException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Coordinates must be positive integers", exception.getMessage());
    }

    @Test
    public void testDrawRectangleCommandWithInvalidArgsThrowsException() {
        String input = "R 14 1 18 3";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Invalid draw rectangle command", exception.getMessage());
    }

    @Test
    public void testDrawRectangleCommandDrawsRectangleOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        String input = "R 14 1 18 3 x";
        Command command = CommandFactory.getCommand(input);
        command.execute(canvas);
        verify(canvas).drawRectangle(14, 1, 18, 3, 'x');
    }

    @Test
    public void testDrawRectangleCommandOnNullCanvasThrowsException() {
        String input = "R 14 1 18 3 x";
        Command command = CommandFactory.getCommand(input);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testCanParseFillCommand() {
        String input = "B 10 3 o";
        Command command = CommandFactory.getCommand(input);
        assertTrue(command instanceof FillCommand);
        assertEquals(10, ((FillCommand) command).getX());
        assertEquals(3, ((FillCommand) command).getY());
        assertEquals('o', ((FillCommand) command).getColor());
    }

    @Test
    public void testFillCommandWithInvalidArgsThrowsException() {
        String input = "B 10 3";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Invalid fill command", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "B -1 3 o",
        "B 10 -3 o"
    })
    public void testFillCommandWithNegativeCoordinatesThrowsException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Coordinates must be positive integers", exception.getMessage());
    }

    @Test
    public void testFillCommandFillsAreaOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        String input = "B 10 3 o";
        Command command = CommandFactory.getCommand(input);
        command.execute(canvas);
        verify(canvas).fill(10, 3, 'o');
    }

    @Test
    public void testFillCommandOnNullCanvasThrowsException() {
        String input = "B 10 3 o";
        Command command = CommandFactory.getCommand(input);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testParseUnknownCommandThrowsException() {
        String input = "X 1 2 3 4";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Unknown command: X 1 2 3 4", exception.getMessage());
    }

    @Test
    public void testParseEmptyCommandThrowsException() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CommandFactory.getCommand(input);
        });
        assertEquals("Command cannot be empty", exception.getMessage());
    }
}
