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
        String sourceFile = parameters[0];
        String targetFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);

        return switch (operation) {
            case "encode" -> encode(key, sourceFile, targetFile);
            case "decode" -> decode(-1 * key, sourceFile, targetFile);
            case "brutforce" -> brutForce();
            default -> RESULT_ERROR;
        };
    }

    private static BufferedReader readFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedReader(path);
        }

        return Files.newBufferedReader(Path.of(TXT_DIR + fileName));
    }

    private static BufferedWriter writeFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedWriter(path);
        }

        return Files.newBufferedWriter(Path.of(TXT_DIR + fileName));
    }

    private static String processFiles(int key, BufferedReader source, BufferedWriter target) throws IOException {
        int len = ALPHABET.length;

        while (source.ready()) {
            String line = source.readLine();
            StringBuilder newLine = new StringBuilder();

            for (char aChar : line.toCharArray()) {
                int idx = ALPHABET_INDEX.getOrDefault(aChar, -1);
                if (idx != -1) {
                    int newIdx = (idx + key + len) % len;
                    newLine.append(ALPHABET[newIdx]);
                } else {
                    newLine.append(aChar);
                }
            }

            target.write(newLine.toString());
            target.write("\n");
        }

        return RESULT_OK;
    }

    private static String encode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        String result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

    private static String decode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        String result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

    private static String brutForce() {

        return RESULT_OK;
    }

}
