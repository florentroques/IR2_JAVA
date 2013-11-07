package fr.upem.java_avance.td3.monitor;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Main {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		
//		PushPathMonitor pathMonitor = new PushPathMonitor(".");

		Path path = Paths.get(".").toAbsolutePath();
		System.out.println(path);
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

		boolean valid = true;
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Path p = (Path) event.context();
				p = p.toAbsolutePath();
				int pathLength = p.getNameCount();

//				String parentDirName = p.getParent().toString();
				String fileName = p.subpath(pathLength-2, pathLength).toString();			

				Kind kind = event.kind();

				if (ENTRY_CREATE.equals(kind)) {
					System.out.println("File created:" + fileName);
				} else if (ENTRY_MODIFY.equals(kind)) {
					System.out.println("File modified:" + fileName);
				} else if (ENTRY_DELETE.equals(kind)) {
					System.out.println("File deleted:" + fileName);
				}
			}
			valid = watchKey.reset();

		} while (valid);
	}
}
