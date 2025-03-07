package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

import java.io.IOException;

public class Exit extends AbstractOperation {

    @Override
    public Result execute(String[] parameters) throws IOException {
        return new Result(ResultCode.OK, "Программа завершена.");
    }
}
