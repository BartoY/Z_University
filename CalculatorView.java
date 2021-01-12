package com.itY.Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView extends JFrame {

    public static void main(String[] args) {
        View();
    }

    public CalculatorView(){};

    public static void View() {
        JFrame frame = new JFrame("计算器1.0");
        frame.setBounds(100, 100, 385, 620);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();


        JTextField textField = new JTextField();
        //textField.setHorizontalAlignment(JTextField.RIGHT);

        textField.setPreferredSize(new Dimension(355, 100));
        textField.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
        panel1.setLayout(new BorderLayout());
        frame.add(panel1, BorderLayout.NORTH);
        panel1.add(textField);


        JButton[] buttons = new JButton[20];

        panel2.setLayout(new FlowLayout());

        //A StringBuffer allows multiple modifications to a string without generating new unused objects

        //Add number button
        buttons[1] = new JButton("C");
        buttons[2] = new JButton("CE");

        buttons[3] = new JButton("/");
        buttons[4] = new JButton("7");
        buttons[5] = new JButton("8");
        buttons[6] = new JButton("9");

        buttons[7] = new JButton("*");
        buttons[8] = new JButton("4");
        buttons[9] = new JButton("5");
        buttons[10] = new JButton("6");

        buttons[11] = new JButton("-");
        buttons[12] = new JButton("1");
        buttons[13] = new JButton("2");
        buttons[14] = new JButton("3");

        buttons[15] = new JButton("+");
        buttons[16] = new JButton(".");
        buttons[17] = new JButton("0");
        buttons[18] = new JButton("=");

        /*
        for (int i = 17; i <= 18; i++) {
            buttons[i].setPreferredSize(new Dimension(175,100));
            buttons[i].setFont(new Font("宋体",Font.CENTER_BASELINE,25));
            panel.add(buttons[i]);

        }
         */
        Listener listener = new Listener(textField);

        for (int i = 1; i <= 18; i++) {
            if (i == 1 || i == 18) {
                buttons[i].setPreferredSize(new Dimension(175, 90));
                buttons[i].setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
                panel2.add(buttons[i]);
                buttons[i].addActionListener(listener);
            } else {

                buttons[i].setPreferredSize(new Dimension(85, 90));
                buttons[i].setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
                panel2.add(buttons[i]);
                buttons[i].addActionListener(listener);
            }
        }

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
