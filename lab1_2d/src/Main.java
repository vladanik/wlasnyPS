import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(Color.green.darker());
        frame.add(new Board());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

class Board extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
//        g2d.drawLine(0, 0, w, h);
//        g2d.setPaint(Color.blue.brighter());
//        g2d.drawOval(w/2 - 50, h/2 - 50, 100, 100);

        //niebo
        g2d.setPaint(Color.blue.brighter().brighter());
        g2d.fillRect(0, 0, w, h);

        //ziemia
        g2d.setPaint(Color.green);
        g2d.fillRect(0, (int) (h * 0.6), w, h);

        //choinka
        g2d.setPaint(Color.green.darker());
        int[] xT = new int[3];
        int[] yT = new int[3];
        xT[0] = 400;
        xT[1] = 430;
        xT[2] = 370;
        yT[0] = 350;
        yT[1] = 400;
        yT[2] = 400;
        //Polygon p = new Polygon(x, y, 3);
        g2d.fillPolygon(new Polygon(xT, yT, 3));
        yT[0] = 380;
        yT[1] = 430;
        yT[2] = 430;
        g2d.fillPolygon(new Polygon(xT, yT, 3));
        yT[0] = 410;
        yT[1] = 460;
        yT[2] = 460;
        g2d.fillPolygon(new Polygon(xT, yT, 3));
        g2d.setPaint(Color.orange.darker());
        g2d.fillRect(395, 460, 10, 30);

        //slonce
        g2d.setPaint(Color.yellow.brighter());
        g2d.fillOval(100, 100, 150, 150);
        g2d.drawLine(175, 90, 175, 40);
        g2d.drawLine(215, 140, 245, 90);
        g2d.drawLine(260, 175, 300, 175);
        g2d.drawLine(215, 215, 245, 265);
        g2d.drawLine(175, 260, 175, 310);
        g2d.drawLine(135, 215, 105, 265);
        g2d.drawLine(90, 175, 40, 175);
        g2d.drawLine(135, 140, 105, 90);
//        int[] xS = new int[72];
//        int[] yS = new int[72];
//
//        for (int i = 0; i < 72; i++) {
//            xS[i] = (int) (((i%2) == 0 ? 150 : 75) * Math.cos(((360 / (72 * i+1) - ((360 / 72 / 2))) * Math.PI / 180))) + 150;
//            yS[i] = (int) (((i%2) == 0 ? 150 : 75) * Math.sin(((360 / (72 * i+1) - ((360 / 72 / 2))) * Math.PI / 180))) + 150;
//        }
//        g2d.setPaint(Color.yellow.brighter());
//        g2d.fillPolygon(xS, yS, 72);

        //dom
        g2d.setPaint(new Color(95, 70, 0));
        g2d.fillRect(700, 300, 200, 200);
        xT[0] = 800;
        xT[1] = 700;
        xT[2] = 900;
        yT[0] = 200;
        yT[1] = 300;
        yT[2] = 300;
        g2d.fillPolygon(xT, yT, 3);
        g2d.setPaint(Color.black);
        g2d.drawLine(700, 300, 900, 300);
        g2d.fillRect(725, 400, 50, 100);
        g2d.setPaint(Color.white);
        g2d.fillOval(760, 445, 10, 10);
        g2d.setPaint(Color.blue.brighter());
        g2d.fillRect(800, 320, 40, 40);
        g2d.fillRect(850, 320, 40, 40);
        g2d.fillRect(800, 370, 40, 40);
        g2d.fillRect(850, 370, 40, 40);

    }
}