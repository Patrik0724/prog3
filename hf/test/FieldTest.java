import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FieldTest {
    private Field f1, f2, f3, f4, f5, f6;
    private ArrayList<Field> board = new ArrayList<>();

    @Before
    public void setUp(){
        f1 = new Field(new Point(0, 0));
        f2 = new Field(new Point(1, 0));
        f3 = new Field(new Point(0, -1));
        f4 = new Field(new Point(2, 0));
        f5 = new Field(new Point(0, 1));
        f6 = new Field(new Point(2, 2));
        board.add(f1);
        board.add(f2);
        board.add(f3);
        board.add(f4);
        board.add(f5);
        board.add(f6);
        for(Field f: board){
            for(Field fi: board){
                f.addNeighbor(fi);
                fi.addNeighbor(f);
            }
        }
    }

    @Test
    public void addNeighborTest(){
        assertSame(f1.getNeighbor(0), f3);
        assertSame(f1.getNeighbor(2), f2);
        assertSame(f1.getNeighbor(3), f5);
        assertNull(f1.getNeighbor(1));
        assertNull(f1.getNeighbor(4));
        assertNull(f1.getNeighbor(5));
    }

    @Test
    public void getNeighborsPositionTest(){
        assertEquals(new Point(1, 0), f1.getNeighborsPosition(2));
        assertEquals(new Point(-1, 1), f1.getNeighborsPosition(4));
    }

    @Test
    public void deleteNeighborTest(){
        f1.deleteNeighbor(f3);
        assertNull(f1.getNeighbor(0));
        f1.deleteNeighbor(f2);
        assertNotNull(f4.getNeighbor(5));
    }

    @Test
    public void getFurthestNeigborTest(){
        assertSame(f1.getFurthestNeighbor(2), f4);
        assertSame(f1.getFurthestNeighbor(3), f5);
        assertSame(f1.getFurthestNeighbor(5), f1);
    }
}
