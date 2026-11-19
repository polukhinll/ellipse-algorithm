
import java.awt.Color;
import java.awt.image.BufferedImage;

public class EllipseRasterizer {

    public static void draw(
            BufferedImage image,
            int xc, int yc,
            int a, int b,
            Color color
    ) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException(
                    "Полуоси должны быть положительными"
            );
        }

        int x = 0;
        int y = b;

        double a2 = (double) a * a;
        double b2 = (double) b * b;

        double dx = 0;
        double dy = 2 * a2 * y;

        // Первая область
        double p1 = b2 - a2 * b + 0.25 * a2;

        while (dx < dy) {
            drawSymmetricPixels(
                    image, xc, yc, x, y, color
            );

            x++;
            dx += 2 * b2;

            if (p1 < 0) {
                p1 += dx + b2;
            } else {
                y--;
                dy -= 2 * a2;
                p1 += dx - dy + b2;
            }
        }

        // Вторая область
        double p2 = b2 * (x + 0.5) * (x + 0.5)
                + a2 * (y - 1) * (y - 1)
                - a2 * b2;

        while (y >= 0) {
            drawSymmetricPixels(
                    image, xc, yc, x, y, color
            );

            y--;
            dy -= 2 * a2;

            if (p2 > 0) {
                p2 += a2 - dy;
            } else {
                x++;
                dx += 2 * b2;
                p2 += dx - dy + a2;
            }
        }
    }

    private static void drawSymmetricPixels(
            BufferedImage image,
            int xc, int yc,
            int x, int y,
            Color color
    ) {
        putPixel(image, xc + x, yc + y, color);
        putPixel(image, xc - x, yc + y, color);
        putPixel(image, xc + x, yc - y, color);
        putPixel(image, xc - x, yc - y, color);
    }

    private static void putPixel(
            BufferedImage image,
            int x, int y,
            Color color
    ) {
        if (x >= 0 && x < image.getWidth()
                && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, color.getRGB());
        }
    }
}
