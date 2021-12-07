import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PossibleNewFieldBuilderTest {
    private Builder b = new PossibleNewFieldBuilder();
    private Field f1, f2, f3, f4;
    private ArrayList<Field> board = new ArrayList<>();
    private ArrayList<Point> coordinates = new ArrayList<>();
    private ArrayList<BufferedImage> images = new ArrayList<>();

    @Before
    public void setUp(){
        f1 = new Field(new Point(0, 0));
        f2 = new Field(new Point(-1, 0));
        f3 = new Field(new Point(1, -1));
        f4 = new Field(new Point(0, -1));
        board.add(f1);
        board.add(f2);
        board.add(f3);
        board.add(f4);
        for(Field f: board){
            for(Field fi: board){
                f.addNeighbor(fi);
                fi.addNeighbor(f);
            }
        }
        b.build(board, coordinates, images);
    }

    @Test
    public void buildTest(){
        assertEquals(3, coordinates.size());
        assertEquals(coordinates.get(0), new Point(0, -2));
        assertEquals(coordinates.get(1), new Point(-1, -2));
        assertEquals(coordinates.get(2), new Point(1, -3));
        Field fi = board.remove(3);
        board.add(new Field(new Point(0, -2)));
        board.add(fi);
        for(Field f: board){
            board.get(4).addNeighbor(f);
            fi.addNeighbor(f);
            f.addNeighbor(board.get(4));
            f.addNeighbor(fi);
        }
        assertFalse(b.build(board, coordinates, images));
    }
}
