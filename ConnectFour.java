package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    public ConnectFour() {
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        initButtons();
        setVisible(true);
    }

    public void initButtons() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setSize(400, 400);
        buttonContainer.setLayout(new GridLayout(6, 7));
        for (int i = 6; i > 0; i--) {
            for (char j = 'A'; j < 'H'; j++) {
                JButton button = new JButton(String.format("%s%d", j, i).toUpperCase());
                button.setFocusPainted(false);
                button.setName(String.format("Button%s%d", j, i));
                buttonContainer.add(button).setFont(font);
            }
        }
        setVisible(true);
        add(buttonContainer);
    }
}