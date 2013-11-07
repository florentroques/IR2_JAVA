package fr.upem.poo.td4.factory;

public class Circle implements Shape {
    private final int centerX;
    private final int centerY;
    private final int radius;

    public Circle(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void draw(CanvasArea area) {
        area.drawEllipse(centerX - radius, centerY + radius, radius * 2,
                radius * 2);
    }
}
