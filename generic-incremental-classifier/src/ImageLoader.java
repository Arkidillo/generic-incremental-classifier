import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    // take filename of image in 'images' folder and return ImageIcon object
    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(".\\images\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static BufferedImage resizeImage(int width, int height, Image image){
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bi;
    }

    // return first image in the 'images' folder.
    public static BufferedImage loadFirstImage() {
        return loadImage("me.jpg");
    }
}