package battleshipmain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Battle extends GameSetUp implements ActionListener {
    JPanel panelL = new JPanel(); // left  panel
    JPanel panelLC = new JPanel(); // left center panel 100 btn

    JPanel panelR = new JPanel(); // right panel
    JPanel panelRC = new JPanel(); // right center panel 100 btn

    
    GiveName name = new GiveName();
    String onoma = name.GiveName();
    
    
    JLabel NameLab = new JLabel(onoma + "'s Board");
    JLabel ComputerLab = new JLabel("Computer's Board");

    public JButton[] b1 = new JButton[101];
    public JButton[] b2 = new JButton[101];

    int board[][];

    boolean flagg = false;
    boolean flag2 = false;

    ArrayList<Integer> rndList = new ArrayList<>();
    ArrayList<Integer> clickList = new ArrayList<>();

    int count1 = 0, count2 = 0;
    int winCount = 0;
    int countComp = 0;

    public JPanel Battle() {
        JPanel mainPanel = new JPanel();
        Init in = new Init();
        Random rnd = new Random();

        board = in.InitMatrix();
        rndList = in.IntList();
        clickList = in.IntList();

        panelLC.setLayout(new GridLayout(10, 10));
        panelRC.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < 100; i++) {
            b1[i] = new JButton("" + i);
            b1[i].setBackground(Color.cyan);

            b2[i] = new JButton("" + i);
            b2[i].setBackground(Color.cyan);
            b2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton t = (JButton) e.getSource();
                    if (clickList.contains(Integer.valueOf(t.getText()))) { // prevent double clicking 
                        clickList.remove(Integer.valueOf(t.getText()));
                        count2++;
                        flag2 = true;
                        flagg = false;

                        int pos = Integer.parseInt(t.getText());
                        for (int j = 0; j < 5; j++) {

                            for (int i = 0; i < board[j].length; i++) {
                                if (pos == board[j][i]) {
                                    winCount++;
                                    flagg = true;
                                    t.setBackground(Color.red);
                                    System.out.println("hit " + t.getText() + " " + count2);
                                    if (winCount == 17) {
                                        JOptionPane.showOptionDialog(null, name+" is the winner!", "Results", JOptionPane.DEFAULT_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                                    }
                                    break;
                                }
                                if ((flagg == false)) {
                                    t.setBackground(Color.blue);
                                    System.out.println("no " + t.getText() + " " + count2);
                                }
                            }
                        }

                        if (flag2 == true) {
                            int x = rnd.RandomHit(rndList);
                            b1[x].setBackground(Color.blue);

                            
                            System.out.println("Battle List2");
                            System.out.println(finalList);
                            
                            if (finalList.contains(x)) {
                                b1[x].setBackground(Color.red);
                                countComp++;
                            }
                            if (countComp == 17) {
                                JOptionPane.showOptionDialog(null, "Computer Wins !", "Results", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                            }

                            count1++;
                            System.out.println("random computer hit " + b1[x].getText() + " " + count1);
                        }
                    }
                }
            });
            panelRC.add(b2[i]);
            panelLC.add(b1[i]);

        }

        panelL.setLayout(new BorderLayout());
        panelL.add(NameLab, BorderLayout.NORTH);
        panelL.add(panelLC, BorderLayout.CENTER);

        panelR.setLayout(new BorderLayout());
        panelR.add(ComputerLab, BorderLayout.NORTH);
        panelR.add(panelRC, BorderLayout.CENTER);

        mainPanel.add(panelL, BorderLayout.WEST);
        mainPanel.add(panelR, BorderLayout.EAST);

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
