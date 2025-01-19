package Language;

import Constants.Constants;

import java.util.ArrayList;

public class LanguageIdentifier {


    public static ArrayList<Character> identifyCharacter(char toIdentify){
        if (Constants.ukrainianAlphabet.contains(Character.toLowerCase(toIdentify))) {
            return  Constants.ukrainianAlphabet;
        }else {
            return Constants.englishAlphabet;
        }
    }
}
