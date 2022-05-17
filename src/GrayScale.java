import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import javax.imageio.*;


public class GrayScale {
    private BufferedImage image;
    private int width;
    private int height;

    private int r;
    private int g;
    private int b;

//    private int[][] maska = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private void p() {
//        maska[0][0] = 0;
//        maska[0][1] = 0;
//        maska[0][2] = 0;
//        maska[1][0] = 0;
//        maska[1][1] = 0;
//        maska[1][2] = 0;
//        maska[2][0] = 0;
//        maska[2][1] = 0;
//        maska[2][2] = 0;
    }

    public GrayScale(String filePath) {
        try {
            File input = new File(filePath + ".jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            p();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GrayScale(int x, int y, int z, String filePath, String nazwa) {
        this(filePath);
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    r = (int) (c.getRed());
                    g = (int) (c.getGreen());
                    b = (int) (c.getBlue());

                    if (r + x >= 0 && r + x <= 255)
                        r += x;
                    if (g + y >= 0 && g + y <= 255)
                        g += y;
                    if (b + z >= 0 && b + z <= 255)
                        b += z;



                    Color newColor = new Color(r, g, b);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            File output = new File(filePath + nazwa + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public GrayScale(String filePath, String nazwa) {
        this(filePath);
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    r = (int) (c.getRed());
                    g = (int) (c.getGreen());
                    b = (int) (c.getBlue());

                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    Color newColor = new Color(r, g, b);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            File output = new File(filePath + nazwa + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public GrayScale(int dc, String filePath, String nazwa) {
        this(filePath);
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    r = (int) (c.getRed());
                    g = (int) (c.getGreen());
                    b = (int) (c.getBlue());

                    int x = (127 / (127 - dc)) * (r - dc);
                    int y = (127 / (127 - dc)) * (g - dc);
                    int z = (127 / (127 - dc)) * (b - dc);

                    if (r + x >= 0 && r + x <= 255)
                        r += x;
                    if (g + y >= 0 && g + y <= 255)
                        g += y;
                    if (b + z >= 0 && b + z <= 255)
                        b += z;

                    Color newColor = new Color(r, g, b);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File(filePath + nazwa + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GrayScale(String filePath, int qwerty) {
        this(filePath);
        try {
            int[] histR = new int[256];
            int[] histG = new int[256];
            int[] histB = new int[256];

            double[] histNormR = new double[256];
            double[] histNormG = new double[256];
            double[] histNormB = new double[256];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    r = (int) (c.getRed());
                    g = (int) (c.getGreen());
                    b = (int) (c.getBlue());

                    histR[r]++;
                    histG[g]++;
                    histB[b]++;
                }
            }
            for (int i = 0; i < 256; i++) {
                histNormR[i] = (double) histR[i] / (width * height);
                histNormG[i] = (double) histG[i] / (width * height);
                histNormB[i] = (double) histB[i] / (width * height);
            }

            System.out.println(histNormR);
            System.out.println(histNormG);
            System.out.println(histNormB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GrayScale(int[][] maska, String filePath, String nazwa) {
        this(filePath);
        try {
            for (int i = 1; i < height - 1; i++) {
                for (int j = 1; j < width - 1; j++) {
                    int pomocR = 0;
                    int pomocG = 0;
                    int pomocB = 0;

                    for (int k = -1; k <= 1 ; k++) {
                        for (int l = -1; l <= 1; l++) {
                            Color c = new Color(image.getRGB(j + k, i + l));
                            r = (int) (c.getRed());
                            g = (int) (c.getGreen());
                            b = (int) (c.getBlue());

                            pomocR += r * maska[k+1][l+1];
                            pomocG += g * maska[k+1][l+1];
                            pomocB += b * maska[k+1][l+1];

                            }
                    }
                    if (pomocR >= 0 && pomocR <= 255)
                        r = pomocR;
                    else
                        r = 0;
                    if (pomocG >= 0 && pomocG <= 255)
                        g = pomocG;
                    else
                        g = 0;
                    if (pomocB >= 0 && pomocB <= 255)
                        b = pomocB;
                    else
                        b = 0;

                    Color newColor = new Color(r, g, b);
                    image.setRGB(j, i, newColor.getRGB());

                }
            }
            File output = new File(filePath + nazwa + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Wpisz scieżkę do pliku: ");
        String path = sc.nextLine();
        System.out.println("Wybierz tryb:");
        System.out.println("1. Oświetlenie");
        System.out.println("2. Przyciemnienie");
        System.out.println("3. Negacja");
        System.out.println("4. Zmiana kontrastu");
        System.out.println("5. Wywołanie histogramu kolorycznego");
        System.out.println("6. Filtr wyostrzający");
        System.out.println("------------------------");
        int tryb = sc.nextInt();
        System.out.println("------------------------");

        if (tryb == 1 || tryb == 2) {
            System.out.println("Wpisz wartość tranformacji od 0 do 255:");
            int radix = sc.nextInt();
            System.out.println("------------------------");
            if (tryb == 1)
                new GrayScale(radix, radix, radix, path, "_oswietlony");
            else
                new GrayScale((radix * (-1)), (radix * (-1)), (radix * (-1)), path, "_przyciemniony");
        } else if (tryb == 3)
            new GrayScale(path, "_negatywny");
        else if (tryb == 4) {
            System.out.println("Wpisz wartość zmieniania kontrastu C od -128 do 127:");
            int dc = sc.nextInt();
            new GrayScale(dc, path, "_zmienionyKontrast");
        } else if (tryb == 5) {
            new GrayScale(path, 1);
        } else if (tryb == 6) {
            int[][] maska = {{0, 0, 0}, {0, 1, -1}, {0, 0, 0}};
            new GrayScale(maska, path, "_wyostrzony");
        }
        else
            System.out.println("Niepoprawna operacja");
    }
}
