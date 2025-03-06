package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Decoder extends AbstractOperation {

    @Override
    public Result execute(String[] parameters)  throws IOException {
        BufferedReader source = readFile(parameters[0]);
        BufferedWriter target = writeFile(parameters[1]);
        int key = -1 * Integer.parseInt(parameters[2]);

        Result result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
//        return new Result(ResultCode.OK, "decode complete successful");
    }
}
