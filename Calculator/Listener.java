package com.sugonYH.Calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Listener implements ActionListener {

    private static JTextField text;
    private StringBuffer s = new StringBuffer();//A StringBuffer allows multiple modifications to a string without generating new unused objects

    public Listener(JTextField tF) {
        text = tF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "C"){
            text.setText("");
            s.delete(0, s.length());
        }else if (command == "CE"){
            if (s.length() != 0) {
                s.delete(s.length() - 1, s.length());
                String str = s.toString();
                text.setText(str);
            }
        }else if (command == "/"){if (s.length() != 0) {// The first number cannot be a division sign

            int length = s.length();
            String over = s.substring(length - 1, length);

            if (!("/".equals(over))) {// Cannot continue to enter two divisions
                s.append("/");
                String str = s.toString();
                text.setText(str);
            }
        }

        }else if (command == "*"){
            if (s.length() != 0) {// The first number cannot be a times
            int length2 = s.length();
            String over2 = s.substring(length2 - 1, length2);

            if (!("*".equals(over2))) {// Cannot continue to enter two times
                s.append("*");
                String str = s.toString();
                text.setText(str);
            }
        }

        }else if (command == "-"){
            if ((s.length() == 0)) {//Enter the first minus sign
                s.append("-");
                String str = s.toString();
                text.setText(str);

            } else {// Starting from the second number, you cannot enter two minus signs consecutively
                int length3 = s.length();
                String over3 = s.substring(length3 - 1, length3);

                if (!("-".equals(over3))) {// Cannot continue to enter two minus signs
                    s.append("-");
                    String str = s.toString();
                    text.setText(str);
                }
            }
        }else if (command == "+"){
            if (s.length() == 0) {//Enter the first plus sign
                s.append("+");
                String str = s.toString();
                text.setText(str);
            } else {// Starting with the second number, two plus signs cannot be entered consecutively
                int length4 = s.length();
                String over4 = s.substring(length4 - 1, length4);

                if (!("+".equals(over4))) {// Cannot continue to enter two plus signs
                    s.append("+");
                    String str = s.toString();
                    text.setText(str);
                }
            }
        }else if(command == "="){
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
                }
//                } else {// Input by keyboard. If it is a symbol plus a number, fill it with zero and assign the input value to SS
//                    if (text.getText().toString().length() == 2) {
//                        StringBuffer stbu = new StringBuffer(text.getText());
//                        ss = stbu.insert(0, 0).toString();
//                    } else {
//                        ss = text.getText().toString();
//                    }
//
//                }

                String index = "[-]|[+]|[/]|[*]";
                String number[] = ss.split(index);//The split() method splits the string based on a given regular expression
                int count = number.length;//The count record is split into segments

                if (count > 2) {
                    text.setText("暂未实现连续计算");

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
                                text.setText("错误");
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

                    text.setText(ss + "=" + result);
                }

            }
        }else {
            s.append(command);
            String str = s.toString();
            text.setText(str);
        }
    }
}
