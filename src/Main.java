
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Растеризация эллипса");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new EllipsePanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class EllipsePanel extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final BufferedImage canvas;

    public EllipsePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        canvas = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
        );

        // Заполняем фон белым цветом
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                canvas.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
