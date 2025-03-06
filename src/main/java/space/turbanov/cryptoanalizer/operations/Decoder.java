package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;

public class Decoder implements Operation {

    @Override
    public Result execute(String[] parameters) {

        return new Result(ResultCode.OK, "decode complete successful");
    }
}
