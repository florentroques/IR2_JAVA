package fr.upem.poo.td4.factory;

public class Line implements Shape {
    private static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

//    private final Point p1;
//    private final Point p2;

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

//    public Line(int x1, int y1, int x2, int y2) {
//        this.p1 = new Point(x1, y1);
//        this.p2 = new Point(x2, y2);
//    }

    Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void draw(CanvasArea area) {
        area.drawLine(x1, y1, x2, y2);
    }
}
