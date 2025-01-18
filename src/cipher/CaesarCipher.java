package cipher;

import constants.Constants;

public class CaesarCipher {

    public String encrypt(String text, int key) {
        key %= Constants.englishAlphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);

            int index = Constants.englishAlphabet.indexOf(lowerChar);
            if (index == -1) {
                result.append(c);
                continue;
            }

            int newIndex = (index + key) % Constants.englishAlphabet.size();
            char encryptedChar = Constants.englishAlphabet.get(newIndex);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }

    public String decrypt(String text, int key) {
        key %= Constants.englishAlphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);

            int index = Constants.englishAlphabet.indexOf(lowerChar);
            if (index == -1) {
                result.append(c);
                continue;
            }

            int newIndex = (index - key) % Constants.englishAlphabet.size();
            char encryptedChar = Constants.englishAlphabet.get(newIndex);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }
}
