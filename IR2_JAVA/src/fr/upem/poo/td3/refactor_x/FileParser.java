package fr.upem.poo.td3.refactor_x;

import static java.nio.charset.StandardCharsets.US_ASCII;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FileParser {

	private FileParser() {}

	private static void parseFile(Path filePath, Consumer<String[]> consumer)
			throws IOException {
		try (BufferedReader reader = Files
				.newBufferedReader(filePath, US_ASCII)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split(" ");
				consumer.accept(lineSplit);
			}
		}
	}

	public static Map<Long, Account> parseAccountsFile(Path filePath)
			throws IOException {
		LinkedHashMap<Long, Account> idToAccountMap = new LinkedHashMap<>();
		parseFile(filePath, (String[] tokens) -> {
		    Account account = new Account(Long.parseLong(tokens[0]),
					Long.parseLong(tokens[3]), tokens[1], tokens[2]);
		    idToAccountMap.put(Long.parseLong(tokens[0]), account);
		});

		return Collections.unmodifiableMap(idToAccountMap);
	}

	public static void parseRecordsFile(Path filePath, Map<Long, Account> idToAccountMap)
			throws IOException {

		parseFile(filePath,
				(String[] tokens) -> {
				    Account accountSender = idToAccountMap.get(Long
							.parseLong(tokens[1]));
				    Account accountReceiver = idToAccountMap.get(Long
							.parseLong(tokens[2]));
					if (accountSender == null || accountReceiver == null) {
						return;
					}

					Record record = new Record(accountSender, accountReceiver,
							Long.parseLong(tokens[0]));
					accountSender.fixBalance(record, -record.getSumTransfer());
					accountReceiver.fixBalance(record, record.getSumTransfer());
				});
	}
}