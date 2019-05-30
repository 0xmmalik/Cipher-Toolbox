package ciphergui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CipherGui {
    private JTextField plainTextCaesar;
    private JButton encodeTextButton;
    private JSpinner keyCaesar;
    private JButton caesarLearn;
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JLabel caesarCipherLabel;
    private JFormattedTextField cipherTextCaesar;
    private JSpinner deKeyCaesar;
    private JButton decodeTextButton;
    private JFormattedTextField keyVig;
    private JFormattedTextField deKeyVig;
    private JButton encodeVig;
    private JFormattedTextField plainVig;
    private JFormattedTextField rotText;
    private JButton rotEcdc;
    private JButton rotLearn;
    private JFormattedTextField alphaPlain;
    private JFormattedTextField alphaAlpha;
    private JButton encAlpha;

    public CipherGui() {
        caesarLearn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "The Caesar Cipher is named as such because\nit was Julius Caesar who first 'invented' it.\nHe used a key of 3 to encode military messages.\n\nThe Caesar Cipher consists of letters being shifted\nto the right a certain amount.\n\nUnfortunately, some special characters cannot be rendered\nby this toolbox and will be replaced by seemingly\narbitrary letters!");
            }
        });

        rotLearn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "The ROT13 cipher is a cipher that rotates\ntext around the alphabet. It is essentially a\nCaesar Cipher with a key of 13. Since the key is\n13, encoding text and decoding it do the same\nthing as they ROTATE the text around the\nalphabet.");
            }
        });


        rotEcdc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = rotText.getText();
                for(int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if(character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char)(character + 13);

                    if(character > 'z')
                        character = (char)(character + 'a' - 'z' - 1);

                    else if(character > 'Z' && character < 'a')
                        character = (char)(character + 'A' - 'Z' - 1);

                    encoded += character;
                }
                JOptionPane.showMessageDialog(null, encoded);
           }
        });

        encAlpha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = alphaPlain.getText().toLowerCase();
                String new_alpha = alphaAlpha.getText();

                if(new_alpha.length() == 26) {
                    for (int i = 0; i < original.length(); i++) {
                        char character = original.charAt(i);
                        if(character >= 'a' && character <= 'z')
                            encoded += new_alpha.charAt(character - 'a');
                        else
                            encoded += character;
                    }
                    JOptionPane.showMessageDialog(null, encoded);

                }
                else
                    JOptionPane.showMessageDialog(null, "The alphabet needs to be 26 chars in length!");
            }
        });

        encodeTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = plainTextCaesar.getText().toLowerCase();
                int kei = (int)(keyCaesar.getValue());
                while(kei < 0)
                    kei += 26;
                kei = kei % 26;

                for(int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if(character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char)(character + kei);

                    if(character > 'z')
                        character = (char)(character + 'a' - 'z' - 1);

                    else if(character > 'Z' && character < 'a')
                        character = (char)(character + 'A' - 'Z' - 1);

                    encoded += character;
                }
                JOptionPane.showMessageDialog(null, encoded);
            }
        });

        decodeTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String decoded = "";
                String original = cipherTextCaesar.getText().toLowerCase();
                int kei = (int)(deKeyCaesar.getValue());
                kei = -kei;
                while(kei < 0)
                    kei += 26;
                kei = kei % 26;

                for(int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if(character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char)(character + kei);

                    if(character > 'z')
                        character = (char)(character + 'a' - 'z' - 1);

                    else if(character > 'Z' && character < 'a')
                        character = (char)(character + 'A' - 'Z' - 1);

                    decoded += character;
                }
                JOptionPane.showMessageDialog(null, decoded);
            }
        });

        encodeVig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = plainVig.getText().toLowerCase();
                String kei = keyVig.getText().toLowerCase().replaceAll("\\W+", "");

                for (int i = 0, j = 0; i < original.length(); i++, j += 0) {
                    char character = original.charAt(i);
                    char addition = kei.charAt(j);

                    addition -= 'a';

                    if (character >= 'a' && character <= 'z') {
                        character = (char) (character + addition);
                        j = (j + 1) % kei.length();
                    }

                    if (character > 'z')
                        character = (char) (character + 'a' - 'z' - 1);

                    else if (character > 'Z' && character < 'a')
                        character = (char) (character + 'A' - 'Z' - 1);

                    encoded += character;
                }
                JOptionPane.showMessageDialog(null, encoded);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cipher Toolbox");
        frame.setContentPane(new CipherGui().panelMain);
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
