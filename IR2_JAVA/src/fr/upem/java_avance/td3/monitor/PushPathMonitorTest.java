package fr.upem.java_avance.td3.monitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.EnumSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PushPathMonitorTest {
  private Path tmpDir;

  @Before
  public void before() throws IOException {
    tmpDir = Files.createTempDirectory("path-monitor-test");
  }
  
  @After
  public void after() {
    tmpDir = null;
  }
  
  @Test
  public void testPushPathMonitorTest() throws IOException {
    Path aValidDirectory = Files.createTempDirectory(tmpDir, null);
    new PushPathMonitor(aValidDirectory, null);
  }
  
  @Test(expected=IOException.class)
  public void testPushPathMonitorTest2() throws IOException {
    Path notADirectory = Files.createTempFile(tmpDir, null, null);
    new PushPathMonitor(notADirectory, null);
  }

  @Test(expected=IOException.class)
  public void testPushPathMonitorTest3() throws IOException {
    Path aDirectory = Files.createTempDirectory(tmpDir, null,
        PosixFilePermissions.asFileAttribute(EnumSet.noneOf(PosixFilePermission.class)));
    new PushPathMonitor(aDirectory, null);
  }
  
  @Test
  public void testEmptyListener() throws IOException {
    Path directory = Files.createTempDirectory(tmpDir, null);
    
    class EmptyPathChangeListener implements PathStateChangeListener {
      @Override
      public void pathChanged(Path path) {
        // do nothing
      }
    }
    
    PushPathMonitor pathMonitor = new PushPathMonitor(directory, new EmptyPathChangeListener());
  }
  
  public static class JumpError extends Error {
    private static final long serialVersionUID = 7102897292057358411L;
  }
  
  @Test(timeout=300)
  public void testAdded() throws IOException, InterruptedException {
    final Path directory = Files.createTempDirectory(tmpDir, null);
    final ArrayList<Path> pathList = new ArrayList<>();
    PushPathMonitor pathMonitor = new PushPathMonitor(directory, new PathStateChangeListener() {
      @Override
      public void pathChanged(Path path) {
        pathList.add(path);
        throw new JumpError();
      }
    });
    final Path[] files = new Path[1];
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(100);
          Path tmpFile = Files.createTempFile(directory, null, null);
          files[0] = tmpFile;
        } catch (InterruptedException|IOException e) {
          throw new AssertionError("error in swpaned thread", e);
        }
      }
    });
    thread.start();
    try {
      pathMonitor.monitor();
      fail();
    } catch(JumpError e) {
      // ok
    }
    thread.join();
    assertEquals(files[0].toAbsolutePath(), pathList.get(0).toAbsolutePath());
  }
  
  @Test(timeout=300)
  public void testDeleted() throws IOException, InterruptedException {
    final Path directory = Files.createTempDirectory(tmpDir, null);
    final Path tmpFile = Files.createTempFile(directory, null, null);
    PushPathMonitor pathMonitor = new PushPathMonitor(directory, new PathStateChangeListener() {
      @Override
      public void pathChanged(Path path) {
        assertEquals(tmpFile.toAbsolutePath(), path.toAbsolutePath());
        throw new JumpError();
      }
    });
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(100);
          Files.delete(tmpFile);
        } catch (InterruptedException|IOException e) {
          throw new AssertionError("error in swpaned thread", e);
        }
      }
    });
    thread.start();
    try {
      pathMonitor.monitor();
      fail();
    } catch(JumpError e) {
      // ok
    }
    thread.join();
  }
  
  @Test(timeout=1500)
  public void testInterrupt() throws IOException {
    final Path directory = Files.createTempDirectory(tmpDir, null);
    PushPathMonitor pathMonitor = new PushPathMonitor(directory, new PathStateChangeListener() {
      @Override
      public void pathChanged(Path path) {
        fail();
      }
    });
    
    final Thread mainThread = Thread.currentThread();
    for(int i=0; i<10; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            throw new AssertionError("spawn thread interrupted ??", e);
          }
          mainThread.interrupt();
        }
      }).start();
      try {
        pathMonitor.monitor();
      } catch (InterruptedException e) {
        return;
      }
      if (mainThread.isInterrupted()) {
        fail("interrupt status should be cleared when exiting monitor()");
      }
    }
  }
}