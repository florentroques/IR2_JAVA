package fr.upem.poo.td4.factory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *  This class represents a window and a canvas.
 *  
 *  The methods {@link #drawLine(int, int, int, int)}, {@link #drawRectangle(int, int, int, int)}
 *  and {@link #drawEllipse(int, int, int, int)} can be used to draw shapes on the canvas.
 *  The color of the shape can be changed by using {@link #useBrush(Brush)} before caling
 *  a method {@code draw*}.
 *  The mouse events can be retrieved using {@link #waitForMouseEvents(MouseCallback)}. 
 *  
 *  All these methods are thread safe.
 */
public class CanvasArea {
    private final JComponent area;
    private final BufferedImage buffer;
    final LinkedBlockingQueue<MouseEvent> eventBlockingQueue =
        new LinkedBlockingQueue<>();
    
    // the following fields can only be changed
    // by the event dispatch thread
    private Color color = Color.BLACK;
    private boolean isOpaque;
    
    /**
     * Create a window of size width x height and a title. 
     * 
     * @param title the title of the window.
     * @param width the width of the window. 
     * @param height the height of the window.
     */
    public CanvasArea(String title, int width, int height) {
      // This should be a factory method and not a constructor
      // but given that this library can be used
      // before static factory have been introduced
      // it's simpler from the user point of view
      // to create a canvas area using new.
      
      BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      @SuppressWarnings("serial")
      JComponent area = new JComponent() {
        @Override
        protected void paintComponent(Graphics g) {
          g.drawImage(buffer, 0, 0, null);
        }
        
        @Override
        public Dimension getPreferredSize() {
          return new Dimension(width, height);
        }
      };
      area.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent event) {
          try {
            eventBlockingQueue.put(event);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      });
      try {
        EventQueue.invokeAndWait(() -> {
          JFrame frame = new JFrame(title);
          area.setOpaque(true);
          frame.setContentPane(area);
          frame.setResizable(false);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
        });
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch(InvocationTargetException e) {
        throw new IllegalStateException(e.getCause());
      }
      this.buffer = buffer;
      this.area = area;
    }
    
    /**
     * Draw a line between coordinate (x1,y1) and (x2, y2).
     * 
     * @param x1 the x coordinate of the first point.
     * @param y1 the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
      post(graphics -> {
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
      });
    }
    
    /**
     * Draw a rectangle using the coordinate of a upper point and a width and an height.
     * 
     * @param x the x coordinate of the upper left of the rectangle.
     * @param y the y coordinate of the upper left of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void drawRectangle(int x, int y, int width, int height) {
      post(graphics -> {
        graphics.setColor(color);
        if (isOpaque) {
          graphics.fillRect(x, y, width, height);
        } else {
          graphics.drawRect(x, y, width, height);
        }
      });
    }
    
    /**
     * Draw an ellipse using the coordinate of a upper point and a width and an height
     * of the rectangle containing the ellipse.
     * 
     * @param x the x coordinate of the upper left of the rectangle.
     * @param y the y coordinate of the upper left of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void drawEllipse(int x, int y, int width, int height) {
      post(graphics -> {
        graphics.setColor(color);
        if (isOpaque) {
          graphics.fillOval(x, y, width, height);
        } else {
          graphics.drawOval(x, y, width, height);
        }
      });
    }
    
    /**
     * A brush is a color and a style of painting (opaque or not).
     * A brush that is opaque will fill the content of the drawing.
     */
    public static class Brush {
      final int red;
      final int green;
      final int blue;
      // corresponding opaque brush, null if the corresponding opaque brush is not computed yet
      Brush opaqueBrush;
      
      Brush(int red, int green, int blue, boolean opaque) {
        this.red = checkColorComponent(red);
        this.green = checkColorComponent(green);
        this.blue = checkColorComponent(blue);
        this.opaqueBrush = (opaque)? this: null;
      }

      private static int checkColorComponent(int component) {
        if (component < 0 || component > 255) {
          throw new IllegalArgumentException("bad component value " + component);
        }
        return component;
      }
      
      /**
       * Creates a new Brush representing a color.
       * 
       * @param red red component of the color of the brush.
       *        the value must be between 0 and 255.
       * @param green green component of the color of the brush.
       *        the value must be between 0 and 255.
       * @param blue blue component of the color of the brush.
       *        the value must be between 0 and 255.
       */
      public Brush(int red, int green, int blue) {
        this(red, green, blue, false);
      }
      
      /**
       * Returns true if the current brush is opaque.
       * @return true if the current brush is opaque.
       */
      public boolean isOpaque() {
        return opaqueBrush == this;
      }
      
      /**
       * Returns a brush with the same color components and a style opaque.
       * @return a brush with the same color components and a style opaque.
       */
      public Brush opaque() {
        if (opaqueBrush != null) {
          return opaqueBrush;
        }
        return opaqueBrush = new Brush(red, green, blue, true);
      }

      /**
       * The brush corresponding to the color red.
       */
      public final static Brush RED = new Brush(255, 0, 0);
      
      /**
       * The brush corresponding to the color green.
       */
      public final static Brush GREEN = new Brush(0, 255, 0);
      
      /**
       * The brush corresponding to the color blue.
       */
      public final static Brush BLUE = new Brush(0, 0, 244);
      
      /**
       * The brush corresponding to the color light gray.
       */
      public final static Brush LIGHT_GRAY = new Brush(192, 192, 192);
      
      /**
       * The brush corresponding to the color gray.
       */
      public final static Brush GRAY = new Brush(128, 128, 128);
      
      /**
       * The brush corresponding to the color drak gray.
       */
      public final static Brush DARK_GRAY = new Brush(64, 64, 64);
      
      /**
       * The brush corresponding to the color black.
       */
      public final static Brush BLACK = new Brush(0, 0, 0);
      
      /**
       * The brush corresponding to the color pink.
       */
      public final static Brush PINK = new Brush(255, 175, 175);
      
      /**
       * The brush corresponding to the color orange.
       */
      public final static Brush ORANGE = new Brush(255, 200, 0);
      
      /**
       * The brush corresponding to the color yellow.
       */
      public final static Brush YELLOW = new Brush(255, 255, 0);
      
      /**
       * The brush corresponding to the color magenta.
       */
      public final static Brush MAGENTA = new Brush(255, 0, 255);
      
      /**
       * The brush corresponding to the color cyan.
       */
      public final static Brush CYAN = new Brush(0, 255, 255);
    }
    
    /**
     * Subsequent use of one method draw* will use the brush taken as argument.
     * @param brush a brush that will be used to draw subsequent shapes.
     */
    public void useBrush(Brush brush) {
      post(graphics -> {
        color = new Color(brush.red, brush.green, brush.blue);
        isOpaque = brush.isOpaque();
      });
    }
    
    
    /**
     *  A functional interface of the mouse callback.
     *  @see CanvasArea#waitForMouseEvents(MouseCallback)
     */
    @FunctionalInterface
    public interface MouseCallback {
      /**
       * Called when the mouse is used inside the canvas.
       * @param x x coordinate of the mouse.
       * @param y y coordinate of the mouse
       * 
       * @see CanvasArea#waitForMouseEvents(MouseCallback)
       */
      public void mouseClicked(int x, int y);
    }
    
    /**
     * Wait for mouse events, the mouseCallback method
     * {@link MouseCallback#mouseClicked(int, int)} 
     * will be called for each mouse event until the window
     * is {@link #close() closed}.
     * 
     * @param mouseCallback a mouse callback.
     */
    public void waitForMouseEvents(MouseCallback mouseCallback) {
      if (EventQueue.isDispatchThread()) {
        throw new IllegalStateException("This method can not be called from the EDT");
      }
      for(;;) {
        MouseEvent mouseEvent;
        try {
          mouseEvent = eventBlockingQueue.take();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          break;
        }
        if (mouseEvent == CLOSE_EVENT) {
          return;
        }
        mouseCallback.mouseClicked(mouseEvent.getX(), mouseEvent.getY());
      }
    }
    
    /**
     * Wait for a given number of milliseconds.
     * @param timeInMillis the length of time to sleep.
     */
    public void sleep(long timeInMillis) {
      try {
        Thread.sleep(timeInMillis);
      } catch(InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    private static final MouseEvent CLOSE_EVENT =
        new MouseEvent(new JButton(), -1, -1, -1, 0, 0, 0, 0, 0, false, 0);
    
    /**
     * Close the window.
     */
    public void close() {
      JFrame frame = (JFrame)SwingUtilities.getRoot(area);
      frame.dispose();
      try {
        eventBlockingQueue.put(CLOSE_EVENT);
      } catch(InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    @FunctionalInterface
    interface Painter {
      public void paint(Graphics2D graphics);
    }
    
    private void post(Painter painter) {
      EventQueue.invokeLater(() -> {
        Graphics2D graphics = buffer.createGraphics();
        try {
          graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          painter.paint(graphics);
          area.paintImmediately(0, 0, area.getWidth(), area.getHeight());
        } finally {
          graphics.dispose();
        }
      });
    }
}