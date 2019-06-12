package ciphergui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public JTextField plainTextCaesar;
    public JButton encodeTextButton;
    public JSpinner keyCaesar;
    public JButton caesarLearn;
    public JPanel panelMain;
    public JTabbedPane tabbedPane1;
    public JLabel caesarCipherLabel;
    public JFormattedTextField cipherTextCaesar;
    public JSpinner deKeyCaesar;
    public JButton decodeTextButton;
    public JFormattedTextField keyVig;
    public JFormattedTextField deKeyVig;
    public JButton encodeVig;
    public JFormattedTextField plainVig;
    public JFormattedTextField rotText;
    public JButton rotEcdc;
    public JButton rotLearn;
    public JFormattedTextField alphaPlain;
    public JFormattedTextField alphaAlpha;
    public JButton encAlpha;
    public JFormattedTextField alphaCiph;
    public JFormattedTextField deAlphaAlpha;
    public JButton decAlpha;

    public Main() {
        caesarLearn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CaesarCipher.learn();
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
                for (int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char) (character + 13);

                    if (character > 'z')
                        character = (char) (character + 'a' - 'z' - 1);

                    else if (character > 'Z' && character < 'a')
                        character = (char) (character + 'A' - 'Z' - 1);

                    encoded += character;
                }
                JOptionPane.showMessageDialog(null, encoded);
            }
        });

        encAlpha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = alphaPlain.getText().toLowerCase();
                String new_alpha = alphaAlpha.getText().toLowerCase();

                if (new_alpha.length() == 26) {
                    for (int i = 0; i < original.length(); i++) {
                        char character = original.charAt(i);
                        if (character >= 'a' && character <= 'z')
                            encoded += new_alpha.charAt(character - 'a');
                        else
                            encoded += character;
                    }
                    JOptionPane.showMessageDialog(null, encoded);

                } else
                    JOptionPane.showMessageDialog(null, "The alphabet needs to be 26 chars in length!");
            }
        });

        encodeTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encoded = "";
                String original = plainTextCaesar.getText().toLowerCase();
                int kei = (int) (keyCaesar.getValue());
                while (kei < 0)
                    kei += 26;
                kei = kei % 26;

                for (int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char) (character + kei);

                    if (character > 'z')
                        character = (char) (character + 'a' - 'z' - 1);

                    else if (character > 'Z' && character < 'a')
                        character = (char) (character + 'A' - 'Z' - 1);

                    encoded += character;
                }
                JOptionPane.showMessageDialog(null, encoded);
            }
        });

        decodeTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String decoded = "";
                String original = cipherTextCaesar.getText().toLowerCase();
                int kei = (int) (deKeyCaesar.getValue());
                kei = -kei;
                while (kei < 0)
                    kei += 26;
                kei = kei % 26;

                for (int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);

                    if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')
                        character = (char) (character + kei);

                    if (character > 'z')
                        character = (char) (character + 'a' - 'z' - 1);

                    else if (character > 'Z' && character < 'a')
                        character = (char) (character + 'A' - 'Z' - 1);

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

        decAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decoded = "";
                String original = alphaCiph.getText().toLowerCase();
                String new_alpha = deAlphaAlpha.getText().toLowerCase();

                for (int i = 0; i < original.length(); i++) {
                    char character = original.charAt(i);
                    int dec_char = 0;
                    dec_char = new_alpha.indexOf(character);
                    decoded += (char) (dec_char + 'a');
                }
                JOptionPane.showMessageDialog(null, decoded);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cipher Toolbox");
        frame.setContentPane(new ciphergui.Main().panelMain);
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
