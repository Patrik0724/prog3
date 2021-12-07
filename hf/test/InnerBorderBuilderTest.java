import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InnerBorderBuilderTest {
    private Field f1, f2, f3, f4, f5, f6, f7;
    private ArrayList<Field> board = new ArrayList<>();
    private ArrayList<Point> coordinates = new ArrayList<>();
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private Builder b = new InnerBorderBuilder();

    @Before
    public void setUp(){
        f1 = new Field(new Point(0, 0));
        f2 = new Field(new Point(0, -1));
        f3 = new Field(new Point(1, -1));
        f4 = new Field(new Point(1, 0));
        f5 = new Field(new Point(0, 1));
        f6 = new Field(new Point(-1, 1));
        f7 = new Field(new Point(-1, 0));
        board.add(f1);
        board.add(f2);
        board.add(f3);
        board.add(f4);
        board.add(f5);
        board.add(f6);
        board.add(f7);
        b.build(board, coordinates, images);
        for(Field f: board){
            for(Field fi: board){
                f.addNeighbor(fi);
                fi.addNeighbor(f);
            }
        }
    }

    @Test
    public void buildTest(){
        b.build(board, coordinates, images);
        assertEquals(6, coordinates.size());
        assertEquals(new Point(0, -1), coordinates.get(0));
        assertEquals(new Point(1, 0), coordinates.get(2));
        assertEquals(new Point(-1, 1), coordinates.get(4));
    }
}
