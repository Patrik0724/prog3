import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FieldBuilderTest {
    private Field f1, f2, f3, f4;
    private ArrayList<Field> board = new ArrayList<>();
    private ArrayList<Point> coordinates = new ArrayList<>();
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private Builder b = new FieldBuilder();

    @Before
    public void setUp(){
        f1 = new Field(new Point(0, 0));
        f2 = new Field(new Point(1, -2));
        f3 = new Field(new Point(1, 0));
        f4 = new Field(new Point(0, -1));
    }

    @Test
    public void buildTest(){
        board.add(f4);
        board.add(f1);
        board.add(f2);
        assertFalse(b.build(board, coordinates, images));
        board.add(f4);
        assertFalse(b.build(board, coordinates, images));
        board.add(f3);
        assertTrue(b.build(board, coordinates, images));
        assertEquals(5, board.size());
        assertEquals(new Point(1, -1), board.get(4).getPosition());
        assertEquals(new Point(0, 1), board.get(3).getPosition());
    }
}
