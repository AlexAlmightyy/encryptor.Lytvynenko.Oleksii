package com.javarush.UI;

import com.javarush.CaesarCipher;
import com.javarush.FileService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener{
    private final JButton encryptButton;
    private final JButton decryptButton;
    private final JButton bruteforceButton;
    private final JTextField filePathField;
    private final JTextField keyField;

    public GUI(){
        setTitle("Caesar Cipher Encrypter");
        setBounds(500, 100, 500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(40, 20));

        JLabel pathLabel = new JLabel("Enter a path to File:");
        filePathField = new JTextField();
        JLabel keyLabel = new JLabel("Enter a Key:");
        keyField = new JTextField();
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        bruteforceButton = new JButton("BruteForce");

        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);
        bruteforceButton.addActionListener(this);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(pathLabel);
        topPanel.add(filePathField);
        topPanel.add(keyLabel);
        topPanel.add(keyField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(encryptButton);
        bottomPanel.add(decryptButton);
        bottomPanel.add(bruteforceButton);

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        filePathField.getDocument().addDocumentListener(new ButtonStateController());
        keyField.getDocument().addDocumentListener(new ButtonStateController());

        updateButtonState();

    }

    private void updateButtonState() {
        boolean isFilePathEmpty = filePathField.getText().trim().isEmpty();
        boolean isKeyEmpty = keyField.getText().trim().isEmpty();
        encryptButton.setEnabled(!isFilePathEmpty && !isKeyEmpty);
        decryptButton.setEnabled(!isFilePathEmpty && !isKeyEmpty);
        bruteforceButton.setEnabled(!isFilePathEmpty);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String filePath = filePathField.getText();
            CaesarCipher caesar = new CaesarCipher();
            FileService fileService = new FileService();

            if (e.getSource() == encryptButton) {
                int key = Integer.parseInt(keyField.getText());
                String encryptedText = caesar.encrypt(fileService.readFile(filePath), key);
                fileService.writeEncryptedFile(filePath, caesar.encrypt(fileService.readFile(filePath), key));
                showMessageDialog("File encrypted successfully!", encryptedText, "Encrypted text");

            } else if (e.getSource() == decryptButton) {
                int key = Integer.parseInt(keyField.getText());
                String decryptedText = caesar.decrypt(fileService.readFile(filePath), key);
                fileService.writeDecryptedFile(filePath, caesar.decrypt(fileService.readFile(filePath), key));
                showMessageDialog("File decrypted successfully!", decryptedText, "Decrypted text");

            } else if (e.getSource() == bruteforceButton) {
                int key = caesar.getKeyWithBruteforce(fileService.readFile(filePath));
                String decryptedText = caesar.decrypt(fileService.readFile(filePath), key);
                fileService.writeBruteForcedFile(filePath, fileService.readFile(filePath), key);
                showMessageDialog("BruteForce was Successful!", decryptedText, "BruteForced text, Key is - " + key);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid key. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }  catch (IOException ex){
            JOptionPane.showMessageDialog(this, "File not found ! Enter a valid path", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessageDialog(String message, String encryptedText, String Encrypted_text) {
        JOptionPane.showMessageDialog(this, message);
        JTextArea textArea = new JTextArea(encryptedText);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(this, scrollPane, Encrypted_text, JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonStateController implements DocumentListener {

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }
    }
}
