import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BorderBuilderTest {
    private Builder b = new BorderBuilder();
    private Field f1, f2, f3, f4, f5;
    private ArrayList<Field> board = new ArrayList<>();
    private ArrayList<Point> coordinates = new ArrayList<>();
    private ArrayList<BufferedImage> images = new ArrayList<>();

    @Before
    public void setUp(){
        f1 = new Field(new Point(0, 0));
        f2 = new Field(new Point(1, 0));
        f3 = new Field(new Point(1, -2));
        f4 = new Field(new Point(0, -1));
        f5 = new Field(new Point(2, -2));
        board.add(f1);
        board.add(f2);
        board.add(f3);
        board.add(f4);
        board.add(f5);
        for(Field f: board){
            for(Field fi: board){
                f.addNeighbor(fi);
                fi.addNeighbor(f);
            }
        }
        b.build(board, coordinates, images);
    }

    @Test
    public void testBuild(){
        assertEquals(coordinates.size(), 12);
        assertEquals(coordinates.get(0), new Point(0, 1));
        assertEquals(coordinates.get(3), new Point(2, -1));
        assertEquals(coordinates.get(6), new Point(1, -3));
    }

}