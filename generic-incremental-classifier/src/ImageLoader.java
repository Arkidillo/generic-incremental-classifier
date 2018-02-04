import javax.swing.*;
import java.awt.*;

public class ImageLoader {

    // take filename of image in 'images' folder and return ImageIcon object
    public static OurImage loadImage(String fileName){
        return new OurImage(new ImageIcon(".\\images\\" + fileName));
    }

    public static OurImage resizeImage(int width, int height, Image image){
        return new OurImage(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT)));

    }

    // return first image in the 'images' folder.
    public static OurImage loadFirstImage() {
        return null;
    }
}