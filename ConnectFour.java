package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectFour extends JFrame {
    int xCount = 0;
    int oCount = 0;

    public ConnectFour() {
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(450, 450);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        initButtons();
        addWindowListener(new CheckOnExit());
        setVisible(true);
    }

    public void initButtons() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setSize(400, 400);
        buttonContainer.setLayout(new GridLayout(6, 7));
        for (int i = 6; i > 0; i--) {
            for (char j = 'A'; j < 'H'; j++) {
                JButton button = new JButton(" ");
                button.setFocusPainted(false);
                button.addActionListener(e -> {
                    if (e.getActionCommand().equals(" ")) {
                        if (xCount == oCount) {
                            button.setText("X");
                            xCount++;
                        } else if (xCount > oCount) {
                            button.setText("O");
                            oCount++;
                        }
                    }
                });
                button.setName(String.format("Button%s%d", j, i));
                buttonContainer.add(button).setFont(font);
            }
        }
        setVisible(true);
        add(buttonContainer);
    }
}

//confirmation window for closing application
class CheckOnExit extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        ClosingWindow checker = new ClosingWindow();
        checker.setVisible(true);
    }

}

class ClosingWindow extends JFrame implements ActionListener {
    public ClosingWindow() {
        setSize(250, 100);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel confirmLabel = new JLabel("Are you sure you want to quit?", SwingConstants.CENTER);
        add(confirmLabel, BorderLayout.CENTER);

        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(new FlowLayout());

        JButton confirmButton = new JButton("Yes");
        confirmButton.addActionListener(this);
        buttonPannel.add(confirmButton);

        JButton cancelButton = new JButton("No");
        cancelButton.addActionListener(this);
        buttonPannel.add(cancelButton);

        add(buttonPannel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Yes")) {
            System.exit(0);
        } else if (action.equals("No")) {
            dispose();
        }
    }
}
