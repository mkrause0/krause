package games.frame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinateConverter extends JFrame {
	
    public static void main(String[] args) {
        CoordinateConverter converter = new CoordinateConverter();
        converter.setVisible(true);
    }
	
    private List<List<Point>> lines;
    private int highlightedLineIndex = -1; // Index der hervorgehobenen Linie
    private int mouseX;
    private int mouseY;

    public CoordinateConverter() {
        lines = new ArrayList<>();

        // Beispielwerte für Linien
        List<Point> line1 = new ArrayList<>();
        line1.add(new Point(0, 0));
        line1.add(new Point(100, 100));
        line1.add(new Point(200, 50));
        lines.add(line1);

        List<Point> line2 = new ArrayList<>();
        line2.add(new Point(50, 150));
        line2.add(new Point(150, 200));
        line2.add(new Point(250, 150));
        lines.add(line2);

        setTitle("Coordinate Converter");
        setSize(500, 500);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
                highlightLine();
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        int xOffset = 50; // Horizontaler Versatz im Fenster
        int yOffset = 50; // Vertikaler Versatz im Fenster
        int scale = 2; // Skalierungsfaktor

        for (int i = 0; i < lines.size(); i++) {
            List<Point> line = lines.get(i);

            for (int j = 0; j < line.size() - 1; j++) {
                Point p1 = line.get(j);
                Point p2 = line.get(j + 1);

                // Umrechnung von Weltkoordinaten in Pixelkoordinaten
                int x1 = xOffset + (p1.x * scale);
                int y1 = yOffset + (p1.y * scale);
                int x2 = xOffset + (p2.x * scale);
                int y2 = yOffset + (p2.y * scale);

                // Hervorhebung der Linie, wenn sich der Mauszeiger darüber befindet
                if (i == highlightedLineIndex) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawLine(x1, y1, x2, y2);
            }
        }

        // Anzeige der x- und y-Koordinaten
        g.setColor(Color.BLACK);
        g.drawString("X: " + mouseX, 10, 40);
        g.drawString("Y: " + mouseY, 10, 60);
    }
    
    private void highlightLine() {
        highlightedLineIndex = -1;

        int xOffset = 50; // Horizontaler Versatz im Fenster
        int yOffset = 50; // Vertikaler Versatz im Fenster
        int scale = 2; // Skalierungsfaktor

        for (int i = 0; i < lines.size(); i++) {
            List<Point> line = lines.get(i);

            for (int j = 0; j < line.size() - 1; j++) {
                Point p1 = line.get(j);
                Point p2 = line.get(j + 1);

                // Umrechnung von Weltkoordinaten in Pixelkoordinaten
                int x1 = xOffset + (p1.x * scale);
                int y1 = yOffset + (p1.y * scale);
                int x2 = xOffset + (p2.x * scale);
                int y2 = yOffset + (p2.y * scale);

                // Überprüfung, ob der Mauszeiger über der Linie liegt
                if (isMouseOverLine(x1, y1, x2, y2)) {
                    highlightedLineIndex = i;
                    int mouseXWorld = (mouseX - xOffset) / scale;
                    int mouseYWorld = (mouseY - yOffset) / scale;
                    System.out.println("X-Weltkoordinate: " + mouseXWorld);
                    System.out.println("Y-Weltkoordinate: " + mouseYWorld);
                    return;
                }
            }
        }
    }

    private boolean isMouseOverLine(int x1, int y1, int x2, int y2) {
        int tolerance = 5; // Toleranz für die Entfernung zur Linie

        // Berechnung der Entfernung des Mauszeigers zur Linie (mit Pythagoras)
        double distance = Math.abs((y2 - y1) * mouseX - (x2 - x1) * mouseY + x2 * y1 - y2 * x1)
                        / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));

        return distance <= tolerance;
    }


}