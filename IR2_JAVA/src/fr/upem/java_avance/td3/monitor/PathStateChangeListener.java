package fr.upem.java_avance.td3.monitor;

import java.nio.file.Path;

@FunctionalInterface
public interface PathStateChangeListener {
	public void pathChanged(Path path);
}