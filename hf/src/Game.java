import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Game extends JPanel{
    private JTextField currentPlayer = new JTextField();
    private Player p1, p2;
    private ArrayList<Builder> builders = new ArrayList<>();
    private ArrayList<BufferedImage> fieldimages = new ArrayList<>();
    private ArrayList<Point> fieldcoordinates = new ArrayList<>();
    private ArrayList<BufferedImage> borderimages = new ArrayList<>();
    private ArrayList<Point> bordercoordinates = new ArrayList<>();
    private ArrayList<BufferedImage> numbers = new ArrayList<>();
    private Point prev;
    private JFrame jf = new JFrame();
    private Map m;

    public class MouseListener1 extends MouseAdapter {
        private Point p;

        public void mousePressed(MouseEvent me) {
            p = new Point(m.getBasis(new Point(me.getX(), me.getY())));
            if (m.getBoard().size() != 32) {
                createTable();
            } else if (p1.getCoordinates().size() == 0 || p2.getCoordinates().size() == 0) {
                putDownSheeps();
            } else {
                play();
            }
            repaint();
        }

        private void createTable() {
            for (int i = 0; i < bordercoordinates.size(); ++i) {
                if (bordercoordinates.get(i).equals(p)) {
                    if (prev.equals(new Point(100, 100))) {
                        prev = p;
                        Field f1 = new Field(prev);
                        m.getBoard().add(f1);
                        builders.get(2).build(m.getBoard(), bordercoordinates, borderimages);
                    } else {
                        Field f2 = new Field(p);
                        m.getBoard().add(f2);
                        builders.get(0).build(m.getBoard(), fieldcoordinates, fieldimages);
                        if (currentPlayer.getText().equals(p1.getName()))
                            currentPlayer.setText(p2.getName());
                        else
                            currentPlayer.setText(p1.getName());
                        prev = new Point(100, 100);
                        if (m.getBoard().size() == 32) {
                            builders.get(3).build(m.getBoard(), bordercoordinates, borderimages);
                            return;
                        }
                        builders.get(1).build(m.getBoard(), bordercoordinates, borderimages);
                    }
                }
            }
        }

        private void putDownSheeps() {
            for (int i = 0; i < bordercoordinates.size(); ++i) {
                if (bordercoordinates.get(i).equals(p)) {
                    if (currentPlayer.getText().equals(p1.getName())) {
                        p1.getCoordinates().add(p);
                        for (Field f : m.getBoard())
                            if (f.getPosition().equals(p))
                                f.addSheeps(16);
                        builders.get(3).build(m.getBoard(), bordercoordinates, borderimages);
                        currentPlayer.setText(p2.getName());
                    } else {
                        p2.getCoordinates().add(p);
                        currentPlayer.setText(p1.getName());
                        bordercoordinates.clear();
                        for (Field f : m.getBoard())
                            if (f.getPosition().equals(p))
                                f.addSheeps(16);
                    }
                    return;
                }
            }
        }

        private void play() {
            if (prev.equals(new Point(100, 100))) {
                for (Field f : m.getBoard()) {
                    if (p.equals(f.getPosition()) && f.getSheeps() > 1) {
                        if ((currentPlayer.getText().equals(p1.getName()) && p1.getCoordinates().contains(f.getPosition())) || (currentPlayer.getText().equals(p2.getName()) && p2.getCoordinates().contains(f.getPosition()))) {
                            m.getBoard().add(f);
                            if (builders.get(4).build(m.getBoard(), bordercoordinates, borderimages))
                                prev = p;
                            return;
                        }
                    }
                }
            } else {
                for (int i = 0; i < bordercoordinates.size(); ++i) {
                    if (bordercoordinates.get(i).equals(p)) {
                        int n = 0;
                        if (currentPlayer.getText().equals(p1.getName())) {
                            p1.getCoordinates().add(p);
                            for (Field f : m.getBoard()) {
                                if (f.getPosition().equals(prev)) {
                                    n = f.getSheeps() / 2;
                                    f.addSheeps(-n);
                                }
                            }
                            for (Field f : m.getBoard()) {
                                if (f.getPosition().equals(p)) {
                                    f.addSheeps(n);
                                }
                            }
                            currentPlayer.setText(p2.getName());
                        } else {
                            p2.getCoordinates().add(p);
                            for (Field f : m.getBoard()) {
                                if (f.getPosition().equals(prev)) {
                                    n = f.getSheeps() / 2;
                                    f.addSheeps(-n);
                                }
                            }
                            for (Field f : m.getBoard()) {
                                if (f.getPosition().equals(p)) {
                                    f.addSheeps(n);
                                }
                            }
                            currentPlayer.setText(p1.getName());
                        }
                        prev = new Point(100, 100);
                        bordercoordinates.clear();
                    }
                }
            }
            if(!p2.canStep(m.getBoard())){
                Result r = new Result(p1);
            }
            else if(!p1.canStep(m.getBoard())){
                Result r = new Result(p2);
            }
        }
    }

    public class SaveButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try {
                FileOutputStream fos = new FileOutputStream("map.txt");
                FileOutputStream fos2 = new FileOutputStream("players.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                oos.writeObject(m);
                ArrayList<Player> players = new ArrayList<>();
                players.add(p1);
                players.add(p2);
                oos2.writeObject(players);
                oos.close();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    public class MainMenuButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            MainMenu mm = new MainMenu();
            jf.dispose();
        }
    }

    public Game(Player player1, Player player2) {
        p1 = player1;
        p2 = player2;
        addNumbers();
        initBuilders();
        prev = new Point(100, 100);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        currentPlayer.setText(p1.getName());
        currentPlayer.setEditable(false);
        jp1.add(currentPlayer);
        jf.add(jp1, BorderLayout.NORTH);
        jf.add(this, BorderLayout.CENTER);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton jb = new JButton("Save");
        jp2.add(jb);
        JButton jb2 = new JButton("Main menu");
        jp2.add(jb2);
        jp2.setBorder(new EmptyBorder(new Insets(0, 700, 0, 700)));
        jf.add(jp2, BorderLayout.SOUTH);
        jf.setVisible(true);
        Point o = new Point(jf.getSize().width / 2, jf.getSize().height / 2);
        try {
            borderimages.add(ImageIO.read(new File("pictures/transparent_border.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bordercoordinates.add(new Point(0, 0));
        repaint();
        m = new Map(o);
        MouseListener1 ml = new MouseListener1();
        addMouseListener(ml);
        jb.addActionListener(new SaveButtonActionListener());
        jb2.addActionListener(new MainMenuButtonActionListener());
    }

    public Game(Map map, ArrayList<Player> players) {
        m = map;
        p1 = players.get(0);
        p2 = players.get(1);
        addNumbers();
        initBuilders();
        prev = new Point(100, 100);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        if((map.getBoard().size() / 4) % 2 != 0 || p1.getCoordinates().size() > p2.getCoordinates().size())
            currentPlayer.setText(p2.getName());
        else
            currentPlayer.setText(p1.getName());
        currentPlayer.setEditable(false);
        jp1.add(currentPlayer);
        jf.add(jp1, BorderLayout.NORTH);
        jf.add(this, BorderLayout.CENTER);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton jb = new JButton("Save");
        jp2.add(jb);
        JButton jb2 = new JButton("Main menu");
        jp2.add(jb2);
        jp2.setBorder(new EmptyBorder(new Insets(0, 700, 0, 700)));
        jf.add(jp2, BorderLayout.SOUTH);
        if (m.getBoard().size() != 32) {
            builders.get(1).build(m.getBoard(), bordercoordinates, borderimages);
        } else if (p1.getCoordinates().size() == 0 || p2.getCoordinates().size() == 0) {
            builders.get(3).build(m.getBoard(), bordercoordinates, borderimages);
        }
        try {
            borderimages.add(ImageIO.read(new File("pictures/transparent_border.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        builders.get(5).build(m.getBoard(), fieldcoordinates, fieldimages);
        jf.setVisible(true);
        MouseListener1 ml = new MouseListener1();
        addMouseListener(ml);
        jb.addActionListener(new SaveButtonActionListener());
        jb2.addActionListener(new MainMenuButtonActionListener());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < fieldimages.size(); ++i) {
            g.drawImage(fieldimages.get(i), m.getImageCenter(i).x - fieldimages.get(i).getWidth() / 2, m.getImageCenter(i).y - fieldimages.get(i).getHeight() / 2, this);
        }
        for (Point p : bordercoordinates) {
            g.drawImage(borderimages.get(0), m.getFieldCenter(p).x - borderimages.get(0).getWidth() / 2, m.getFieldCenter(p).y - borderimages.get(0).getHeight() / 2, this);
        }
        for (Point p : p1.getCoordinates()) {
            g.drawImage(p1.getPic(), m.getFieldCenter(p).x - p1.getPic().getWidth() / 2, m.getFieldCenter(p).y - p1.getPic().getHeight() / 2, this);
        }
        for (Point p : p2.getCoordinates()) {
            g.drawImage(p2.getPic(), m.getFieldCenter(p).x - p2.getPic().getWidth() / 2, m.getFieldCenter(p).y - p2.getPic().getHeight() / 2, this);
        }
        for (Field f : m.getBoard()) {
            if (f.getSheeps() != 0)
                g.drawImage(numbers.get((int) (Math.log(f.getSheeps()) / Math.log(2))), m.getFieldCenter(f.getPosition()).x + 5, m.getFieldCenter(f.getPosition()).y + 5, this);
        }
    }

    public void addNumbers() {
        try {
            numbers.add(ImageIO.read(new File("pictures/1.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            numbers.add(ImageIO.read(new File("pictures/2.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            numbers.add(ImageIO.read(new File("pictures/4.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            numbers.add(ImageIO.read(new File("pictures/8.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            numbers.add(ImageIO.read(new File("pictures/16.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBuilders(){
        Builder fb = new FieldBuilder();
        Builder bb = new BorderBuilder();
        Builder nfb = new PossibleNewFieldBuilder();
        Builder ibb = new InnerBorderBuilder();
        Builder psb = new PossibleStepBuilder();
        Builder fb2 = new FieldBuilder2();
        builders.add(fb);
        builders.add(bb);
        builders.add(nfb);
        builders.add(ibb);
        builders.add(psb);
        builders.add(fb2);
    }
}