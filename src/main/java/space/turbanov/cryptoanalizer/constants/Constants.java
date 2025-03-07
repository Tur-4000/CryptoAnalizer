package space.turbanov.cryptoanalizer.constants;

import java.io.File;

public class Constants {

    public static final String TXT_DIR =
            System.getProperty("user.dir")
            + File.separator
            + "texts"
            + File.separator;

    public static final String ENCODE = "encode";
    public static final String DECODE = "decode";
    public static final String BRUTEFORCE = "bruteforce";
    public static final String EXIT = "exit";
}
