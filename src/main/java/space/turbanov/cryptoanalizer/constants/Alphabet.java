package space.turbanov.cryptoanalizer.constants;

import java.util.HashMap;
import java.util.Map;

public class Alphabet
{
    private static final String lat = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cyr = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String SYMBOLS = ".,”’:;#@$^&*()[]{}<>-!?\"'—\\|/_+= ";
    public static final char[] ALPHABET = (lat + cyr + SYMBOLS).toCharArray();
    public static final Map<Character, Integer> ALPHABET_INDEX = new HashMap<>();

    static {
        for (int i = 0; i < ALPHABET.length; i++) {
            ALPHABET_INDEX.put(ALPHABET[i], i);
        }
    }
}
