import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PossibleStepBuilderTest {
    private Builder b = new PossibleStepBuilder();
    private ArrayList<Field> board = new ArrayList<>();
    private ArrayList<Point> coordinates = new ArrayList<>();
    private ArrayList<BufferedImage> images = new ArrayList<>();

    @Before
    public void setUp(){
        Builder b2 = new FieldBuilder();
        board.add(new Field(new Point(0, 0)));
        board.add(new Field(new Point(1, 0)));
        b2.build(board, coordinates, images);
        board.add(new Field(new Point(0, -2)));
        board.add(new Field(new Point(1, -2)));
        b2.build(board, coordinates, images);
    }

    @Test
    public void buildTest(){
        board.get(0).addSheeps(4);
        board.add(board.get(0));
        b.build(board, coordinates, images);
        assertEquals(4, coordinates.size());
        assertEquals(coordinates.get(0), new Point(0, -2));
        assertEquals(coordinates.get(1), new Point(1, -1));
        assertEquals(coordinates.get(2), new Point(1, 0));
        assertEquals(coordinates.get(3), new Point(0, 1));
    }
}
