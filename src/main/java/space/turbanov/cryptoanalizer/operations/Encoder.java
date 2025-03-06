package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

public class Encoder implements Operation {

    @Override
    public Result execute(String[] parameters) {

        return new Result(ResultCode.OK, "encode complete successful");
    }
}
