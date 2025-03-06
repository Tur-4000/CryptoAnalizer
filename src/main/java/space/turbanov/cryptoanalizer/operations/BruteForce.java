package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

import java.io.IOException;

public class BruteForce extends AbstractOperation {

    // TODO: написать метод brutForce()
    @Override
    public Result execute(String[] parameters)  throws IOException {

        return new Result(ResultCode.OK, "operation complete successful");
    }
}
