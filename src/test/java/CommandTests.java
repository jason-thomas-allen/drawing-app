import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import com.example.Drawable;
import com.example.commands.Command;
import com.example.commands.DrawLineCommand;
import com.example.commands.DrawRectangleCommand;
import com.example.commands.FillCommand;
import com.example.commands.InitializeCanvasCommand;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTests {

    @Test
    public void testInitializeCanvasCommandInitializesCanvasWidthAndHeight() {
        Drawable canvas = mock(Drawable.class);
        Command command = new InitializeCanvasCommand("30 15");
        command.execute(canvas);
        verify(canvas).initialize(30, 15);
    }

    @Test
    public void testInitializeCanvasCommandOnNullCanvasThrowsException() {
        Command command = new InitializeCanvasCommand("30 15");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testDrawLineCommandDrawsLineOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        Command command = new DrawLineCommand("1 2 6 2 x");
        command.execute(canvas);
        verify(canvas).drawLine(1, 2, 6, 2, 'x');
    }

    @Test
    public void testDrawLineCommandOnNullCanvasThrowsException() {
        Command command = new DrawLineCommand("1 2 6 2 x");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testDrawRectangleCommandDrawsRectangleOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        Command command = new DrawRectangleCommand("14 1 18 3 x");
        command.execute(canvas);
        verify(canvas).drawRectangle(14, 1, 18, 3, 'x');
    }

    @Test
    public void testDrawRectangleCommandOnNullCanvasThrowsException() {
        Command command = new DrawRectangleCommand("14 1 18 3 x");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }

    @Test
    public void testFillCommandFillsAreaOnCanvas() {
        Drawable canvas = mock(Drawable.class);
        Command command = new FillCommand("10 3 o");
        command.execute(canvas);
        verify(canvas).fill(10, 3, 'o');
    }

    @Test
    public void testFillCommandOnNullCanvasThrowsException() {
        Command command = new FillCommand("10 3 o");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(null);
        });
        assertEquals("Canvas cannot be null", exception.getMessage());
    }
}
