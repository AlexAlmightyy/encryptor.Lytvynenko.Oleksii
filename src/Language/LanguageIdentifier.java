package Language;

import constants.Constants;

import java.util.ArrayList;

public class LanguageIdentifier {


    public static ArrayList<Character> identifyCharacter(char toIdentify){
        if (Constants.ukrainianAlphabet.contains(Character.toLowerCase(toIdentify))) {
            return  Constants.ukrainianAlphabet;
        }else {
            return Constants.englishAlphabet;
        }
    }

    private static Character identifyAlphabet(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                return c;
            }
        }

        return null;
    }
}
