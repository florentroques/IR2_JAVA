package fr.upem.poo.td3.refactor_x;

import java.util.Map;

public class ProcessOutStream {
    public static void display(Map<Long, Account> map) {
        for (Account account : map.values()) {
            System.out.println(account);

            for (Record record : account.getListOfLastRecords()) {
                if (record == null) {
                    continue;
                }

                System.out.println(record);
            }
        }
    }
}
