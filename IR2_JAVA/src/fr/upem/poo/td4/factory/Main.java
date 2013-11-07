package fr.upem.poo.td4.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static void help() {
        System.out.println("Usage : [line|rectangle] x1 y1 x2 y2");
        System.out.println("Usage : file");
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 5 && args.length != 1) {
            help();
            return;
        }

        InputStream in = System.in;
        if (args.length == 1) {
            try {
                in = new FileInputStream(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }

        Scanner scanner = new Scanner(in);

        CanvasArea area = new CanvasArea("hello canvas area", 800, 600);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineSplitted = line.split(" ");

            try {
//                Shape shape = ShapeFactory.create(
//                        lineSplitted[0],
//                        Integer.parseInt(lineSplitted[1]),
//                        Integer.parseInt(lineSplitted[2]),
//                        Integer.parseInt(lineSplitted[3]),
//                        Integer.parseInt(lineSplitted[4]));
                Shape shape = ShapeFactory.create(lineSplitted);

                shape.draw(area);
            } catch (ShapeCreationException e) {
                help();
            }
        }

        scanner.close();
    }
}
