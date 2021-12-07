import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p1, p2;
    ArrayList<Field> board = new ArrayList<>();

    @Before
    public void setUp(){
        p1 = new Player(Color.white, "egyes");
        p2 = new Player(Color.orange, "kettes");
        Field f1 = new Field(new Point(0, 0));
        Field f2 = new Field(new Point(1, 0));
        Field f3 = new Field(new Point(2, -2));
        Field f4 = new Field(new Point(0, 1));
        Field f5 = new Field(new Point(-1, 0));
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
        p1.getCoordinates().add(f3.getPosition());
        f3.addSheeps(2);
        p2.getCoordinates().add(f1.getPosition());
        f1.addSheeps(2);
    }

    @Test
    public void canStepTest(){
        assertFalse(p1.canStep(board));
        assertTrue(p2.canStep(board));
        board.get(1).addSheeps(1);
        board.get(3).addSheeps(1);
        board.get(4).addSheeps(1);
        assertFalse(p2.canStep(board));
    }
}
