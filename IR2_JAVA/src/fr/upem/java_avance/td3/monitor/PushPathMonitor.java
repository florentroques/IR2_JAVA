package fr.upem.java_avance.td3.monitor;

import java.nio.file.Path;

public class PushPathMonitor {
	private final Path path;

	public PushPathMonitor(Path path, PathStateChangeListener pscl) {
		this.path = path;
		
	}

	
}
