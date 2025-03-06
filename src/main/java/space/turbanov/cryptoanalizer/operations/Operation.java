package space.turbanov.cryptoanalizer.operations;

import space.turbanov.cryptoanalizer.entity.Result;

public interface Operation {

    Result execute(String[] parameters);
}
