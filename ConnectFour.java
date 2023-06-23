package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectFour extends JFrame {
    // game tracking stuff

    private final int NUMBER_OF_ROWS = 6;
    private final int NUMBER_OF_COLUMS = 7;
    JButton[][] gameField = new JButton[NUMBER_OF_ROWS][NUMBER_OF_COLUMS];
    String player = "X";
    int position;

    StateChecker referee = new StateChecker();

    //UX settings
    Color lightGreen = new Color(174, 213, 130);
    Font cellFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
    Font resetFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);


    //UI components
    public ConnectFour() {
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(450, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        initButtons();
        addWindowListener(new CheckOnExit());
        setVisible(true);
    }

    public void initButtons() {
        // buttons grid
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMS, 3, 3));

        // space for reset button
        JPanel bottom = new JPanel();
        add(bottom, BorderLayout.SOUTH);

        // reset button settings
        JButton resetButton = new JButton();
        resetButton.setName("ButtonReset");
        resetButton.setSize(200, 100);
        resetButton.setBackground(new Color(255, 255, 0));
        resetButton.setText("RESET");
        resetButton.addActionListener(e -> resetAllButtons(gameField));
        resetButton.setFont(resetFont);
        bottom.add(resetButton, BorderLayout.CENTER);


        //cell button settings
        for (int i = NUMBER_OF_ROWS; i > 0; i--) {
            position = 0;
            for (char j = 'A'; j < 'H'; j++) {
                JButton button = new JButton(" ");
                button.setName(String.format("Button%s%d", j, i));
                button.setFocusPainted(false);
                gameField[NUMBER_OF_ROWS - i][position] = button;
                button.addActionListener(e -> {
                    JButton clickedButton = (JButton) e.getSource();
                    int colNum = 0;
                    for (JButton[] jButtons : gameField) {
                        for (int y = 0; y < jButtons.length; y++) {
                            if (jButtons[y].getName().equals(clickedButton.getName())) {
                                colNum = y;
                            }
                        }
                    }
                    //filling of cell
                    for (int k = NUMBER_OF_ROWS - 1; k >= 0; k--) {
                        if (gameField[k][colNum].getText().equals(" ")) {
                            gameField[k][colNum].setText(player);
                            referee.checkFourInRow(gameField, player);
                            referee.checkFourInColum(colNum, player, gameField);
                            referee.diagonalCheck(gameField, player);
                            switchPlayer();
                            break;
                        }
                    }
                });

                //button color settings
                button.setBackground(lightGreen);

                //adding complete button
                buttonContainer.add(button).setFont(cellFont);
                position++;
            }
        }
        add(buttonContainer);
    }

    private void resetAllButtons(JButton[][] gameField) {
        player = "X";
        for (JButton[] row : gameField) {
            for (JButton button : row) {
                button.setText(" ");
                button.setBackground(lightGreen);
                button.setEnabled(true);
            }
        }
    }
    private void switchPlayer() {
        player = player.equals("X") ? "O" : "X";
    }
}

//confirmation window for closing application
class CheckOnExit extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        ClosingWindow checker = new ClosingWindow();
        checker.setVisible(true);
    }
}

//confirmation window for closing application
class ClosingWindow extends JFrame implements ActionListener {
    public ClosingWindow() {
        setSize(250, 100);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel confirmLabel = new JLabel("Are you sure you want to quit?", SwingConstants.CENTER);
        add(confirmLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton confirmButton = new JButton("Yes");
        confirmButton.addActionListener(this);
        confirmButton.setFocusPainted(false);
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("No");
        cancelButton.addActionListener(this);
        cancelButton.setFocusPainted(false);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
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
