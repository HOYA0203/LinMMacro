import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineListener;

public class CalculatorRGB {
	public int returnCalcRGB(Color c) {
		// warning
		if ((c.getRed() > 100 && c.getGreen() < 30 && c.getBlue() < 30) 
				|| (c.getRed() > 150 && c.getGreen() < 10 && c.getBlue() < 10)) {
			System.out.println(c.getRed() + "/" + c.getGreen() + "/" + c.getBlue());
			return 1;
		}

		// blue light 12
		else if (c.getRed() == 0 && c.getGreen() == 222 && c.getBlue() == 255) {
			return 12;
		}
		// orange light 13
		else if (c.getRed() == 255 && c.getGreen() == 114 && c.getBlue() == 0) {
			return 13;
		}
		// red light 14
		else if (c.getRed() == 255 && c.getGreen() == 23 && c.getBlue() == 23) {
			return 14;
		}
		// letter 15
		else if (c.getRed() == 255 && c.getGreen() == 43 && c.getBlue() == 42) {
			return 15;
		}
		
		// ¸¸¿¥ 16 
		else if (c.getRed() < 35) {
			return 16;
		}

		// sward 17
		else if (178 <= c.getRed() && c.getRed() <= 192 && 
				178 <= c.getGreen() && c.getGreen() <= 192 && 
				178 <= c.getBlue() && c.getBlue() <= 192) {
			return 17;
		}

		return 0;
	}
	
	public int calcRGB(String fileName) {
		BufferedImage image;
		fileName += ".jpg";
		int width;
		int height;
		int returnValue = 0; // return value
		try {
			File input = new File("screenshot/" + fileName);
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color c = new Color(image.getRGB(j, i));
//					 System.out.println(c.getRed() + "/" + c.getGreen() + "/" + c.getBlue());
					returnValue += returnCalcRGB(c);
				}
			}
		} catch (IOException e) {
			System.err.println("CalculatorRGB : calcRGB : IOException");
		}
//		System.out.println(returnValue);
		return returnValue;
	}

	// public int getEqualsValue(String fileName) {
	// String screenShotImageFileName = "screenshot/" + fileName + ".jpg";
	// String originalImageFileName = "screenshot/" + fileName + "Compare.jpg";
	// BufferedImage image;
	// BufferedImage image2;
	// int width;
	// int height;
	// int equalsValue = 0;
	//
	// try {
	// File input = new File(screenShotImageFileName);
	// File input2 = new File(originalImageFileName);
	// image = ImageIO.read(input);
	// image2 = ImageIO.read(input2);
	// width = image.getWidth();
	// height = image.getHeight();
	//
	// for (int i = 0; i < height; i++) {
	// for (int j = 0; j < width; j++) {
	// Color c = new Color(image.getRGB(j, i));
	// Color c2 = new Color(image2.getRGB(j, i));
	// if (c.getBlue() == c2.getBlue()
	// && c.getGreen() == c2.getGreen()
	// && c.getRed() == c2.getRed()) {
	// equalsValue++;
	// }
	// }
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// System.err.println("CalculatorRGB : getEqualsValue : IOException");
	// }
	// return equalsValue; // 4356/4356 || 1628/863
	// }
}
