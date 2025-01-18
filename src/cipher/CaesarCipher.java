package cipher;

import constants.Constants;

public class CaesarCipher {

    public String encrypt(String text, int key) {
        key = Math.abs(key);
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
        key = Math.abs(key);
        key %= Constants.englishAlphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);

            int index = Constants.englishAlphabet.indexOf(lowerChar);
            if (index == -1) {
                result.append(c);
                continue;
            }

            int newIndex = index - key;
            if(newIndex < 0){
                newIndex += Constants.englishAlphabet.size();
            }
            char encryptedChar = Constants.englishAlphabet.get(newIndex);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }

    public int getKeyWithBruteforce(String text){
        double[] charFrequencies = getCharFrequencies(text);


        int bestKey = 0;
        double minDifference = Double.MAX_VALUE;

        for (int key = 0; key < Constants.englishAlphabet.size(); key++) {

            double[] shiftedFrequencies = shiftFrequencies(charFrequencies, key);


            double difference = calculateDifference(shiftedFrequencies, Constants.englishFrequencies);


            if (difference < minDifference) {
                minDifference = difference;
                bestKey = key;
            }
        }

        return bestKey;
    }

    private double[] getCharFrequencies(String text) {
        double[] frequencies = new double[Constants.englishAlphabet.size()];
        int[] charCounts = new int[Constants.englishAlphabet.size()];
        int totalChars = 0;

        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);
            int index = Constants.englishAlphabet.indexOf(lowerChar);
            if (index >= 0) {
                charCounts[index]++;
                totalChars++;
            }
        }

        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = totalChars > 0 ? (double) charCounts[i] / totalChars : 0.0;
        }

        return frequencies;
    }

    private double[] shiftFrequencies(double[] frequencies, int shift) {
        int size = frequencies.length;
        double[] shifted = new double[size];
        for (int i = 0; i < size; i++) {
            shifted[i] = frequencies[(i + shift) % size];
        }
        return shifted;
    }

    private double calculateDifference(double[] frequencies1, double[] frequencies2) {
        double difference = 0.0;

        for (int i = 0; i < frequencies1.length; i++) {
            difference += Math.abs(frequencies1[i] - frequencies2[i]);
        }

        return difference;
    }


}
