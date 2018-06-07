package Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

public abstract class ImageTools 
{
	public static byte[] toByteArray(ImageView iv) throws IOException
	{
		BufferedImage bImage = SwingFXUtils.fromFXImage(iv.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", s);
		return s.toByteArray();	
	}
}
