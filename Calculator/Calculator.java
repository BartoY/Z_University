package com.sugonYH.Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculate();
    }

    public static void Calculate() {

        JFrame frame = new JFrame("计算器1.0");
        frame.setBounds(100, 100, 385, 620);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();


        JTextField textField = new JTextField();
        //textField.setHorizontalAlignment(JTextField.RIGHT);

        textField.setPreferredSize(new Dimension(355, 100));

        JButton[] buttons = new JButton[20];


        panel1.setLayout(new BorderLayout());
        frame.add(panel1, BorderLayout.NORTH);

        panel2.setLayout(new FlowLayout());

        StringBuffer s = new StringBuffer();//A StringBuffer allows multiple modifications to a string without generating new unused objects

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


        textField.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
        panel1.add(textField);

        /*
        for (int i = 17; i <= 18; i++) {
            buttons[i].setPreferredSize(new Dimension(175,100));
            buttons[i].setFont(new Font("宋体",Font.CENTER_BASELINE,25));
            panel.add(buttons[i]);

        }
         */

        for (int i = 1; i <= 18; i++) {
            if (i == 1 || i == 18) {
                buttons[i].setPreferredSize(new Dimension(175, 90));
                buttons[i].setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
                panel2.add(buttons[i]);
            } else {

                buttons[i].setPreferredSize(new Dimension(85, 90));
                buttons[i].setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
                panel2.add(buttons[i]);
            }
        }

        //Monitor, which processes the event source and displays it to a text box

        // Button 'C', after listening to the button, clear
        buttons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                s.delete(0, s.length());
            }
        });

        // Button 'CE', listen to button backspace
        buttons[2].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (s.length() != 0) {
                    s.delete(s.length() - 1, s.length());
                    String str = s.toString();
                    textField.setText(str);
                }
            }
        });

        buttons[3].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (s.length() != 0) {// The first number cannot be a division sign

                    int length = s.length();
                    String over = s.substring(length - 1, length);

                    if (!("/".equals(over))) {// Cannot continue to enter two divisions
                        s.append("/");
                        String str = s.toString();
                        textField.setText(str);
                    }
                }
            }
        });

        buttons[4].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("7");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[5].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("8");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[6].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("9");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[7].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (s.length() != 0) {// The first number cannot be a times
                    int length2 = s.length();
                    String over2 = s.substring(length2 - 1, length2);

                    if (!("*".equals(over2))) {// Cannot continue to enter two times
                        s.append("*");
                        String str = s.toString();
                        textField.setText(str);
                    }
                }

            }
        });

        buttons[8].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("4");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[9].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("5");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[10].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("6");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[11].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ((s.length() == 0)) {//Enter the first minus sign
                    s.append("-");
                    String str = s.toString();
                    textField.setText(str);

                } else {// Starting from the second number, you cannot enter two minus signs consecutively
                    int length3 = s.length();
                    String over3 = s.substring(length3 - 1, length3);

                    if (!("-".equals(over3))) {// Cannot continue to enter two minus signs
                        s.append("-");
                        String str = s.toString();
                        textField.setText(str);
                    }
                }
            }
        });

        buttons[12].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("1");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[13].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("2");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[14].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("3");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[15].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (s.length() == 0) {//Enter the first plus sign
                    s.append("+");
                    String str = s.toString();
                    textField.setText(str);
                } else {// Starting with the second number, two plus signs cannot be entered consecutively
                    int length4 = s.length();
                    String over4 = s.substring(length4 - 1, length4);

                    if (!("+".equals(over4))) {// Cannot continue to enter two plus signs
                        s.append("+");
                        String str = s.toString();
                        textField.setText(str);
                    }
                }
            }
        });

        buttons[16].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append(".");
                String str = s.toString();
                textField.setText(str);
            }
        });

        buttons[17].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.append("0");
                String str = s.toString();
                textField.setText(str);
            }
        });

        // Click the button "=" to calculate the result
        buttons[18].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (s.length() != 0) {
                    String ss = null;

                    // The first number cannot be an equal sign
                    if (s.toString().length() != 0) {

                        // The judgment is entered in THE GUI page. If it is a symbol plus a number, fill it with zero and assign the value to SS
                        if (s.toString().length() == 2) {
                            ss = s.insert(0, 0).toString();
                        } else {
                            ss = s.toString();
                        }

                    } else {// Input by keyboard. If it is a symbol plus a number, fill it with zero and assign the input value to SS
                        if (textField.getText().toString().length() == 2) {
                            StringBuffer stbu = new StringBuffer(textField.getText());
                            ss = stbu.insert(0, 0).toString();
                        } else {
                            ss = textField.getText().toString();
                        }

                    }

                    String index = "[-]|[+]|[/]|[*]";
                    String number[] = ss.split(index);//The split() method splits the string based on a given regular expression
                    int count = number.length;//The count record is split into segments

                    if (count > 2) {
                        textField.setText("暂未实现连续计算");

                    } else {
                        Double num[] = new Double[count];

                        for (int i = 0; i < count; i++) {
                            num[i] = Double.parseDouble(number[i]);

                        }

                        double result = 0;

                        if (ss.contains("*")) {
                            result = num[0];

                            for (int i = 1; i < count; i++) {
                                result *= num[i];
                            }
                        }
                        if (ss.contains("/")) {
                            result = num[0];

                            for (int i = 1; i < count; i++) {
                                if (num[i] == 0) {
                                    textField.setText("错误");
                                    return;
                                } else {
                                    result /= num[i];
                                }


                            }
                        }

                        if (ss.contains("-")) {
                            result = num[0];

                            for (int i = 1; i < count; i++) {
                                result -= num[i];
                            }
                        }

                        if (ss.contains("+")) {
                            result = num[0];

                            for (int i = 1; i < count; i++) {
                                result += num[i];
                            }
                        }

                        textField.setText(ss + "=" + result);
                    }

                }


            }
        });


        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}
