package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class ImageOutput {
    public byte[] checksum;
    public String pathname;
    public ShapeOutput shape;

    public ImageOutput(BufferedImage image, String fileType, String pathname) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, fileType, outputStream);
            byte[] data = outputStream.toByteArray();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            checksum = md.digest();
        } catch (Exception e) {
            System.out.println("ERROR: Failed to write buffered image to byte array for md5: " + e.toString());
        }

        shape = new ShapeOutput(image);
        this.pathname = pathname;
    }
}
