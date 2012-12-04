package utility;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {
	
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(Util.class);
    
    public static byte[] convertImagetoByteArray(Image image, String type)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        try {
            ImageIO.write((RenderedImage)image, type, baos);
        } catch (IOException e) {
            LOG.error("Error while converting an image to byte[]");
        } 
        return  baos.toByteArray();
    }
    
    public static Image convertByteArraytoImage(byte[] byteArray, String type)
    {
        ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
        Image image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            LOG.error("Error while converting a byte[] to image");
        }  
        
        return image;
    }


}
