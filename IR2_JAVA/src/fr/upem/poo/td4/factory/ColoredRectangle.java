package fr.upem.poo.td4.factory;

public class ColoredRectangle extends Rectangle {
    ColoredRectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(CanvasArea area) {
        area.useBrush(CanvasArea.Brush.BLACK.opaque());
        super.draw(area);
        area.useBrush(CanvasArea.Brush.BLACK);
    }

}
