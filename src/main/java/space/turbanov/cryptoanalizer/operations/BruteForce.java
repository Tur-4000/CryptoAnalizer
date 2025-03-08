package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.constants.Alphabet;
import space.turbanov.cryptoanalizer.entity.Result;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BruteForce extends AbstractOperation {

    // TODO: отрефакторить BrutForce(). на удивление шустро работает
    /*
    * - Генерируем словарь из русских и английских слов
    * - читаем первые 100 строк из зашифрованного файла источника в строку
    * дальше в цикле:
    * - вычитанную строку дешифруем с ключом key в строку
    * - разделяем на слова и помещаем в List
    * - итерируемся по листу и ищем совпадающие слова в словаре
    * - если счётчик совпадений со словарём 10 завершаем цикл и дешифруем исходный файл с найденным ключом
    * - если совпадений со словарём нет или меньше 10 инкрементируем key и идём на следующую итерацию
    * */
    @Override
    public Result execute(String[] parameters)  throws IOException {
        BufferedReader source = getBufferedReader(parameters[0]);
        source.mark(1_000_000);

        BufferedWriter target = getBufferedWriter(parameters[1]);
        Map<String, Integer> RuEnDict = generateDict();
        String first100Lines = read100Line(source);

        int key = 1;
        while (true) {
            System.out.println("Декодирую с ключом: " + key);
            int count = 0;

            Scanner scanner = new Scanner(first100Lines);
            StringBuilder decoded = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String decodedLine = processLine(line, -1 * key);
                decoded.append(decodedLine);
                decoded.append(" ");
            }
            scanner.close();

            String decodedPart = decoded.toString();
            String[] decodedWords = decodedPart.split(" ");

            for (String word : decodedWords) {
                if (RuEnDict.get(word) != null) {
                    count += 1;
                }
            }

            if (count >= 10) {
                source.reset();

                Result result = processFiles(-1 * key, source, target);

                source.close();
                target.close();

                return result;
            }

            key += 1;
        }
    }

    private Map<String, Integer> generateDict() {
        try (BufferedReader brR = getBufferedReader("russian-words.txt");
             BufferedReader brE = getBufferedReader("english-words.txt")) {

            Map<String, Integer> words = new HashMap<>();
            String word;

            while (brR.ready()) {
                word = brR.readLine();
                words.put(word, 1);
            }

            while (brE.ready()) {
                word = brE.readLine();
                words.put(word, 1);
            }

            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String read100Line(BufferedReader source) throws IOException {
        StringBuilder firstLines = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            String line = source.readLine();
            firstLines.append(line);
            firstLines.append("\n");
        }

        return firstLines.toString();
    }

    // TODO: не работает
    private String removeSymbols(String text) {
        char[] symbols = Alphabet.SYMBOLS.toCharArray();
        for (char symbol : symbols) {
            text = text.replaceAll(String.valueOf(symbol), "");
        }

        return text;
    }
}
