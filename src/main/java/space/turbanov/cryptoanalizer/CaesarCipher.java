package space.turbanov.cryptoanalizer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CaesarCipher
{
    private static final String TXT_DIR = System.getProperty("user.dir") + File.separator + "texts" + File.separator;
    private static final String RESULT_OK = "OK";
    private static final String RESULT_ERROR = "ERROR";
    private static final String lat = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cyr = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String symbols = ".,”’:;#@$^&*()[]{}<>-!?\"'—\\|/_+= ";
    private static final char[] ALPHABET = (lat + cyr + symbols).toCharArray();
    private static final Map<Character, Integer> ALPHABET_INDEX = new HashMap<>();

    static {
        for (int i = 0; i < ALPHABET.length; i++) {
            ALPHABET_INDEX.put(ALPHABET[i], i);
        }
    }

    public static void main(String[] args) {
        String operation = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);

        try {
            String result = doOperation(operation, parameters);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(RESULT_ERROR);
        }
    }

    private static String doOperation(String operation, String[] parameters) throws IOException {
        int key = Integer.valueOf(parameters[2]);
        String sourceFile = parameters[0];
        String targetFile = parameters[1];

        return switch (operation) {
            case "encode" -> encode(key, sourceFile, targetFile);
            case "decode" -> decode(key, sourceFile, targetFile);
            case "brutforce" -> brutForce();
            default -> RESULT_ERROR;
        };
    }

    private static BufferedReader readFile(String fileName) throws IOException {
        return Files.newBufferedReader(Path.of(TXT_DIR + fileName));
    }

    private static BufferedWriter writeFile(String fileName) throws IOException {
        return Files.newBufferedWriter(Path.of(TXT_DIR + fileName));
    }

    private static String encode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        while (source.ready()) {
            String line = source.readLine();
            StringBuilder newLine = new StringBuilder();
            for (char aChar : line.toCharArray()) {
                int idx = ALPHABET_INDEX.getOrDefault(aChar, -1);
                if (idx != -1) {
                    newLine.append(ALPHABET[(idx + key) % ALPHABET.length]);
                } else {
                    newLine.append(aChar);
                }
            }
            target.write(newLine.toString());
            target.write("\n");
        }

        source.close();
        target.close();

        return RESULT_OK;
    }

    private static String decode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        while (source.ready()) {
            String line = source.readLine();
            StringBuilder newLine = new StringBuilder();
            for (char aChar : line.toCharArray()) {
                int idx = ALPHABET_INDEX.getOrDefault(aChar, -1);
                if (idx != -1) {
                    int newIdx = (idx - key) < 0 ? ALPHABET.length + (idx - key) : (idx - key);
                    newLine.append(ALPHABET[newIdx]);
                } else {
                    newLine.append(aChar);
                }
            }
            target.write(newLine.toString());
            target.write("\n");
        }

        source.close();
        target.close();

        return RESULT_OK;
    }

    private static String brutForce() {

        return RESULT_OK;
    }

}
