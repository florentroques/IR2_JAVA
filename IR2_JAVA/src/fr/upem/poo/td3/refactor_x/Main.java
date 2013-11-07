package fr.upem.poo.td3.refactor_x;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException,
            InterruptedException {
        if (args.length != 2) {
            System.out.println("Usage : <files>");
            return;
        }

        Path accountsFilePath = Paths.get(args[0]);
        Path recordsFilePath = Paths.get(args[1]);

        Map<Long, Account> map = FileParser.parseAccountsFile(accountsFilePath);
        FileParser.parseRecordsFile(recordsFilePath, map);
        ProcessOutStream.display(map);
    }

}
