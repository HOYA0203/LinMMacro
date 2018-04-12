import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MouseController {
	Robot robot = new Robot();

	private int randomX = 0;
	private int randomY = 0;
	private int value = 27;

	public MouseController() throws AWTException { /* .. */
	}

	/** 베르(1780, 640) **/
	public void beleu() {
		randomX = (int) (Math.random() * 60);
		randomY = (int) (Math.random() * 60);

		robot.mouseMove(1780 + randomX, 640 + randomY);
		click();
		click();
		click();

		robot.delay((250 + value)); // value = 27

		robot.mouseMove(1780 + randomX, 640 + randomY);
		click();
		click();
		click();

		System.out.println("[베  르 성 공]");
	}

	/** 베르(1360, 640) **/
	public void teleport() {
		randomX = (int) (Math.random() * 60);
		randomY = (int) (Math.random() * 60);

		robot.mouseMove(1360 + randomX, 640 + randomY);
		click();

		System.out.println("[텔 성 공]");
	}

	/** letter */
	public void openOption() {
		int randomX = (int) (Math.random() * 22);
		int randomY = (int) (Math.random() * 18);
		robot.mouseMove(1828 + randomX, 71 + randomY);
		click();
	}

	public void readLetter() {
		randomX = (int) (Math.random() * 30);
		randomY = (int) (Math.random() * 20);
		robot.mouseMove(1612 + randomX, 336 + randomY);
		click();
	}

	public void closeOption() {
		randomX = (int) (Math.random() * 85);
		randomY = (int) (Math.random() * 25);
		robot.mouseMove(1625 + randomX, 680 + randomY);
		click();

		randomX = (int) (Math.random() * 22);
		randomY = (int) (Math.random() * 18);
		robot.mouseMove(1828 + randomX, 71 + randomY);
		click();
	}
	
	/** manner mode */
	public void mannerMode() {
		//915, 70 950, 105 35 35
		//1100, 615 1180 640 80 25
		//1830, 70 1845, 90 15 20
		// click portion
		randomX = (int) (Math.random() * 35);
		randomY = (int) (Math.random() * 35);
		robot.mouseMove(915 + randomX, 70 + randomY);
		click();
		
		// click manner mode
		randomX = (int) (Math.random() * 80);
		randomY = (int) (Math.random() * 25);
		robot.mouseMove(1100 + randomX, 615 + randomY);
		click();
		
		// x click
		randomX = (int) (Math.random() * 15);
		randomY = (int) (Math.random() * 20);
		robot.mouseMove(1830 + randomX, 70 + randomY);
		click();
	}
	
	public void clickAuto() {
		randomX = (int) (Math.random() * 20);
		randomY = (int) (Math.random() * 20);
		robot.mouseMove(1555 + randomX, 545 + randomY);
		click();
	}
	
	public void clickF4() {
		randomX = (int) (Math.random() * 20);
		randomY = (int) (Math.random() * 20);
		robot.mouseMove(1755 + randomX, 465 + randomY);
		click();
	}
	
	public void clickF8() {
		randomX = (int) (Math.random() * 20);
		randomY = (int) (Math.random() * 20);
		robot.mouseMove(1381 + randomX, 662 + randomY);
		click();
	}
	
	public void swapArrow() {
		randomX = (int) (Math.random() * 35);
		randomY = (int) (Math.random() * 25);
		robot.mouseMove(625 + randomX, 55 + randomY);
		click();
		
		robot.delay(300);
		randomX = (int) (Math.random() * 30);
		randomY = (int) (Math.random() * 10);
		robot.mouseMove(800 + randomX, 665 + randomY);
		click();
		
		robot.delay(300);
		randomX = (int) (Math.random() * 10);
		randomY = (int) (Math.random() * 10);
		robot.mouseMove(955 + randomX, 145 + randomY);
		click();
		
	}
	
	public void findLevUp(int val1) {
		mouseMove(val1);
	}

	public void findLevUp(int random, int val1, int val2) {
		switch (random) {
		case 1:
			mouseMove(val1);
			break;
		case 2:
			mouseMove(val2);
			break;
		default:
			break;
		}
	}

	public void findLevUp(int random, int val1, int val2, int val3, int val4) {
		switch (random) {
		case 1:
			mouseMove(val1);
			break;
		case 2:
			mouseMove(val2);
			break;
		case 3:
			mouseMove(val3);
			break;
		case 4:
			mouseMove(val4);
			break;
		default:
			break;
		}
	}

	/** 매크로 클릭 -> 사냥터까지 이동 **/
	public void mouseMove(int val1) {
		robot.delay(937 + value);
		robot.mouseMove(420, val1);// 1
		click();
	}
	
	public void mouseMove(int x, int y) {
		robot.mouseMove(x, y);
		robot.delay(250);
		click();
	}

	private void click() {
		robot.delay(200 + value); // value = 27
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(250 + value); // value = 27
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void sendMessage(String message, boolean bKakao) {
		if (bKakao) {
			robot.mouseMove(1030, 1000);
			click();
			type(message);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
	}

	private void type(int i) {
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}

	private void type(String s) {
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}
}
