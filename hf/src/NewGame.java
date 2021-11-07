import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame extends JFrame{
    static private Color[] colors = {Color.white, Color.blue, Color.yellow, Color.green, Color.red};
    static private String[] names = {"white", "blue", "yellow", "green", "red"};
    private JComboBox<String> cb1, cb2;
    JTextField tf1 = new JTextField("Player1");
    JTextField tf2 = new JTextField("Player2");
    public class StrartButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            setVisible(false);
            Color c1, c2;
            c1 = colors[cb1.getSelectedIndex()];
            c2 = colors[cb2.getSelectedIndex()];
            Player p1 = new Player(c1, tf1.getText());
            Player p2 = new Player(c2, tf2.getText());
            Game g = new Game(p1, p2);
        }
    }
    public NewGame() {
        super("New Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        cb1 = new JComboBox<>(names);
        cb2 = new JComboBox<>(names);
        JButton jb = new JButton("Start");
        tf1.setColumns(20); tf2.setColumns(20);
        jp1.add(tf1); jp1.add(cb1);
        jp2.add(tf2); jp2.add(cb2);
        jp3.add(jb);
        jp3.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
        add(jp1, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);
        add(jp3, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        ActionListener al = new StrartButtonActionListener();
        jb.addActionListener(al);
    }
}
