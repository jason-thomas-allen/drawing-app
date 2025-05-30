import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Canvas;

public class CanvasTests {
    @Test
    public void testCanCreateCanvas() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        assertNotNull(canvas);
    }

    @Test
    public void testCanCreateCanvasWithCorrectDimensions() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        assertEquals(3, canvas.getWidth());
        assertEquals(3, canvas.getHeight());
    }

    @Test
    public void testCanDisplayCanvas() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        String expected =
            "-----\n" +
            "|   |\n" +
            "|   |\n" +
            "|   |\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanDisplayCanvas20x4() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        String expected =
            "----------------------\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanDrawMultipleShapes() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        canvas.drawLine(1, 2, 6, 2, 'x');
        canvas.drawLine(6, 3, 6, 4, 'x');
        canvas.drawRectangle(14, 1, 18, 3, 'x');

        String expected =
        "----------------------\n" +
        "|             xxxxx  |\n" +
        "|xxxxxx       x   x  |\n" +
        "|     x       xxxxx  |\n" +
        "|     x              |\n" +
        "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillEmptyCanvas() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.fill(1, 1, 'o');
        String expected =
            "-----\n" +
            "|ooo|\n" +
            "|ooo|\n" +
            "|ooo|\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillOutsideCanvasNoFill() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.fill(4, 4, 'o');
        String expected =
            "-----\n" +
            "|   |\n" +
            "|   |\n" +
            "|   |\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillInsideRectangle() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        canvas.drawRectangle(14, 1, 18, 3, 'x');
        canvas.fill(15, 2, 'o');
        String expected =
        "----------------------\n" +
        "|             xxxxx  |\n" +
        "|             xooox  |\n" +
        "|             xxxxx  |\n" +
        "|                    |\n" +
        "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillOnRectangle() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        canvas.drawRectangle(14, 1, 18, 3, 'x');
        canvas.fill(15, 3, 'o');
        String expected =
        "----------------------\n" +
        "|             ooooo  |\n" +
        "|             o   o  |\n" +
        "|             ooooo  |\n" +
        "|                    |\n" +
        "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillOnLines() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        canvas.drawLine(1, 2, 6, 2, 'x');
        canvas.drawLine(6, 3, 6, 4, 'x');
        canvas.drawRectangle(14, 1, 18, 3, 'x');
        canvas.fill(1, 2, 'o');

        String expected =
        "----------------------\n" +
        "|             xxxxx  |\n" +
        "|oooooo       x   x  |\n" +
        "|     o       xxxxx  |\n" +
        "|     o              |\n" +
        "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanFillAroundBoundaryLines() {
        Canvas canvas = new Canvas();
        canvas.initialize(20, 4);
        canvas.drawLine(1, 2, 6, 2, 'x');
        canvas.drawLine(6, 3, 6, 4, 'x');
        canvas.drawRectangle(14, 1, 18, 3, 'x');
        canvas.fill(10, 3, 'o');

        String expected =
        "----------------------\n" +
        "|oooooooooooooxxxxxoo|\n" +
        "|xxxxxxooooooox   xoo|\n" +
        "|     xoooooooxxxxxoo|\n" +
        "|     xoooooooooooooo|\n" +
        "----------------------";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testCanDrawLine() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawLine(2, 2, 3, 2, '.');
        String expected =
            "-----\n" +
            "|   |\n" +
            "| ..|\n" +
            "|   |\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testDrawLineBeyondCanvasBoundsClips() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawLine(2, 2, 5, 2, '.');
        String expected =
            "-----\n" +
            "|   |\n" +
            "| ..|\n" +
            "|   |\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testDrawLineCanDrawForwardsOrBackwards() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawLine(3, 2, 2, 2, '.');
        String forward = canvas.display();

        canvas.initialize(3, 3);
        canvas.drawLine(2, 2, 3, 2, '.');
        String backward = canvas.display();
        assertEquals(forward, backward);
    }

    @Test
    public void testCanDrawRectangle() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawRectangle(1, 1, 3, 3, '.');
        String expected =
            "-----\n" +
            "|...|\n" +
            "|. .|\n" +
            "|...|\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testDrawRectangleBeyondCanvasBoundsClips() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawRectangle(1, 1, 5, 5, '.');
        String expected =
            "-----\n" +
            "|...|\n" +
            "|.  |\n" +
            "|.  |\n" +
            "-----";
        assertEquals(expected, canvas.display());
    }

    @Test
    public void testDrawRectangleCanDrawForwardsOrBackwards() {
        Canvas canvas = new Canvas();
        canvas.initialize(3, 3);
        canvas.drawRectangle(1, 1, 3, 3, '.');
        String forward = canvas.display();

        canvas.initialize(3, 3);
        canvas.drawRectangle(3, 3, 1, 1, '.');
        String backward = canvas.display();
        assertEquals(forward, backward);
    }
}
