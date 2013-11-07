package fr.upem.poo.td4.factory;

public class ShapeFactory {

    private ShapeFactory() {
    }

//    public static Rectangle createRectangle(int x1, int y1, int x2, int y2) {
//        return new Rectangle(x1, y1, x2, y2);
//    }
//
//    public static Line createLine(int x1, int y1, int x2, int y2) {
//        return new Line(x1, y1, x2, y2);
//    }

    public static Shape create(String[] args) throws ShapeCreationException {
//    public static Shape create(String shapeName, int x1, int y1, int x2, int y2) throws ShapeCreationException {
        Shape shape;
        String shapeName = args[0];
        
        int[] coords = new int[args.length-1];
        
        for (int i = 1; i < args.length; i++) {
            coords[i-1] = Integer.parseInt(args[i]);
        }

        switch (shapeName) {
        case "line":
            shape = new Line(coords[0] ,coords[1], coords[2], coords[3]);
            break;

        case "rectangle":
            shape = new Rectangle(coords[0] ,coords[1], coords[2], coords[3]);
            break;
            
        case "circle":
            shape = new Circle(coords[0] ,coords[1], coords[2]);
            break;

        default:
            throw new ShapeCreationException();
        }

        return shape;
    }
}
