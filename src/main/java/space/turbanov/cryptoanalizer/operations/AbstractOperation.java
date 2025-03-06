package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.constants.Alphabet;
import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static space.turbanov.cryptoanalizer.constants.Constants.TXT_DIR;

public abstract class AbstractOperation implements Operation {

    @Override
    public abstract Result execute(String[] parameters) throws IOException;

    public Result processFiles(int key, BufferedReader source, BufferedWriter target) throws IOException {
        while (source.ready()) {
            String line = source.readLine();
            String processedLine = processLine(line, key);
            target.write(processedLine);
            target.write("\n");
        }

        return new Result(ResultCode.OK, "operation complete successful");
    }

    private String processLine(String line, int key) {
        StringBuilder processedLine = new StringBuilder();

        for (char aChar : line.toCharArray()) {
            int index = Alphabet.ALPHABET_INDEX.getOrDefault(aChar, -1);
            processedLine.append(shiftSymbol(index, key, aChar));
        }

        return processedLine.toString();
    }

    private char shiftSymbol(int index, int offset, char symbol) {
        int length = Alphabet.ALPHABET.length;

        if (index != -1) {
            int shiftedIndex = (index + offset + (Math.abs(offset) * length)) % length;

            return Alphabet.ALPHABET[shiftedIndex];
        }

        return symbol;
    }

    public BufferedReader readFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedReader(path);
        }

        return Files.newBufferedReader(Path.of(TXT_DIR + fileName));
    }

    public BufferedWriter writeFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedWriter(path);
        }

        return Files.newBufferedWriter(Path.of(TXT_DIR + fileName));
    }
}
