import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public class NewGameButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            setVisible(false);
            NewGame ng = new NewGame();
        }
    }
    public class ExitButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            System.exit(0);
        }
    }
    public MainMenu(){
        super("Battle Sheep");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jp = new JPanel(new GridLayout(3, 1));
        JButton jb1 = new JButton("New Game");
        JButton jb2 = new JButton("Load Game");
        JButton jb3 = new JButton("Exit");

        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));

        add(jp);
        pack();
        setVisible(true);

        ActionListener al1 = new ExitButtonActionListener();
        jb3.addActionListener(al1);

        ActionListener al2 = new NewGameButtonActionListener();
        jb1.addActionListener(al2);
    }
}
