package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;

import java.io.IOException;

public interface Operation {

    Result execute(String[] parameters) throws IOException;
}
