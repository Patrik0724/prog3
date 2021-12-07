import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    private Point origo;
    private ArrayList<Field> board = new ArrayList<>();
    public Map(Point o){
        origo = o;
    }
    public ArrayList<Field> getBoard(){
        return board;
    }
    public Point getBasis(Point p) {
        double q = (p.x - origo.x) * 2.0 / 3 / 50.0;
        double r = ((p.x - origo.x) * (-1.0 / 3) + (p.y - origo.y) * Math.sqrt(3) / 3) / 50.0;
        return roundBasis(q, r);
    }
    public Point roundBasis(double q, double r) {
        double s = -q - r;
        int s_round = (int) Math.round(s);
        int r_round = (int) Math.round(r);
        int q_round = (int) Math.round(q);
        double q_diff = Math.abs(q_round - q);
        double r_diff = Math.abs(r_round - r);
        double s_diff = Math.abs(s_round - s);
        if (q_diff > r_diff && q_diff > s_diff)
            q_round = -r_round - s_round;
        else if (r_diff > s_diff)
            r_round = -q_round - s_round;
        return new Point(q_round, r_round);
    }
    public Point getFieldCenter(Point vector) {
        double x = 50 * (1.5 * vector.x) + origo.x;
        double y = 50 * (Math.sqrt(3) * vector.x / 2.0 + Math.sqrt(3) * vector.y) + origo.y;
        return new Point((int) x, (int) y);
    }
    public Point getImageCenter(int i) {
        Point p1 = getFieldCenter(board.get(4 * i).getPosition());
        Point p2 = getFieldCenter(board.get(4 * i + 1).getPosition());
        Point p3 = getFieldCenter(board.get(4 * i + 2).getPosition());
        Point p4 = getFieldCenter(board.get(4 * i + 3).getPosition());
        return new Point((p1.x + p2.x + p3.x + p4.x) / 4, (p1.y + p2.y + p3.y + p4.y) / 4);
    }
}
