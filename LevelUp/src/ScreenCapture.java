import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenCapture {
	public void fullScreenCapture(String name, int count) {
		Robot robot;
		String format = "jpg";
		String fullFileName = name + count + "." + format;
		try {
			robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			robot.delay(200);
			ImageIO.write(screenFullImage, format, new File("screenshot/" + fullFileName));
			System.out.println("A full screenshot saved! [ " + fullFileName + " ]");
		} catch (AWTException e) {
			System.err.println("ScreenCapture : fullScreenCapture : AWTException");
		} catch (IOException e) {
			System.err.println("ScreenCapture : fullScreenCapture : IOException");
		} catch (NullPointerException e) {
			System.err.println("ScreenCapture : fullScreenCapture : NullPointerException");
		} catch (Exception e) {
			System.err.println("ScreenCapture : fullScreenCapture : Exception");
		}
	}
	
	public void screenCapture(int x, int y, int w, int h, String fileName) {
		Robot robot;
		String format = "jpg";
		String partFileName = fileName + "." + format;
		try {
			robot = new Robot();
			Rectangle screenRect = new Rectangle(x, y, w, h);
			BufferedImage screenPartImage = robot.createScreenCapture(screenRect);
			robot.delay(200);
			ImageIO.write(screenPartImage, format, new File("screenshot/" + partFileName));
			System.out.println("A screenshot saved! [ " + partFileName + " ]");
		} catch (AWTException e) {
			System.err.println("ScreenCapture : screenCapture : AWTException");
		} catch (IOException e) {
			System.err.println("ScreenCapture : screenCapture : IOException");
		} catch (NullPointerException e) {
			System.err.println("ScreenCapture : screenCapture : NullPointerException");
		} catch (Exception e) {
			System.err.println("ScreenCapture : screenCapture : Exception");
		}
	}
}
