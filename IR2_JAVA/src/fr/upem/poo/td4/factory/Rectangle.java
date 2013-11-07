package fr.upem.poo.td4.factory;

import static java.lang.Math.*;

public class Rectangle implements Shape {
//    private final int x;
//    private final int y;
//    private final int width;
//    private final int height;
//    
//    public Rectangle(int x, int y, int width, int height) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//    }

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(CanvasArea area) {
        area.drawRectangle(x1, y1, abs(x2 - x1), abs(y2 - y1));
    }
}
