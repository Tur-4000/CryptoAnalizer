package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Encoder extends AbstractOperation {

    @Override
    public Result execute(String[] parameters) throws IOException {
        BufferedReader source = getBufferedReader(parameters[0]);
        BufferedWriter target = getBufferedWriter(parameters[1]);
        int key = Integer.parseInt(parameters[2]);

        Result result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }
}
