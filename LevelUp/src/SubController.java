import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

public class SubController {
	 ScreenCapture sc = new ScreenCapture();
	 CalculatorRGB CalRGB = new CalculatorRGB();
	Robot robot = new Robot();
	Color c = null;
	
	public SubController() throws AWTException {}
	
	public void test(int x, int y) {
		c = robot.getPixelColor(x, y);
		System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
	}
	
	public String getNxNRGB(int x, int y, int w, int h) {
		int pvpCount = 0;
		int portionCnt = 0;
		int safeCnt = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				c = robot.getPixelColor(x + i, y + j);
				if((c.getRed() > 100 && c.getGreen() < 30 && c.getBlue() < 30) 
				|| (c.getRed() > 150 && c.getGreen() < 10 && c.getBlue() < 10)) {
					pvpCount++;
				}
				
				if(c.getRed() == 214 && c.getGreen() == 165 && c.getBlue() == 83) {
					portionCnt++;
				}
				
				if(c.getRed() == 255 && c.getGreen() == 244 && c.getBlue() == 224) {
					safeCnt++;
				}
//				System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
			}
		}

		if (5 < pvpCount && pvpCount < 8) {
			return "pvp";
		}
		
		if (portionCnt > 8) {
			return "portion";
		}
		
		if (safeCnt > 4) {
			return "safe";
		}
		
		return "nothing";
	}
	
	public String get1x1RGB(int x, int y) {
		c = robot.getPixelColor(x, y);

//		 System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
		/* 1585, 90 */
		// blue
		if (c.getRed() == 0 && c.getGreen() == 222 && c.getBlue() == 255) {
			return "blue";
		}
		// orange
		else if (c.getRed() == 255 && c.getGreen() == 114 && c.getBlue() == 0) {
			return "orange";
		}
		// red
		else if (c.getRed() == 255 && c.getGreen() == 23 && c.getBlue() == 23) {
			return "red";
		}

		/* 1855, 65 / 1628, 346 */
		// letter
		else if (c.getRed() == 255 && c.getGreen() == 42 && c.getBlue() == 42) {
			return "new";
		}

		/* 1560, 670 */
		// portion
		else if (c.getRed() == 154 && c.getGreen() == 154 && c.getBlue() == 152) {
			return "noPortion";
		}
		
		// arrow
		else if (c.getRed() == 120 && c.getGreen() == 119 && c.getBlue() == 119) {
			return "noArrow";
		}
		
		else if (c.getRed() == 77 && c.getGreen() == 77 && c.getBlue() == 73) {
			return "noArrow";
		}
		
		else if (c.getRed() == 30 && c.getGreen() == 136 && c.getBlue() == 231) {
			return "safe";
		}
		
		else if (c.getRed() >= 250 && c.getGreen() >= 160
				&& c.getGreen() <= 230 && c.getBlue() >= 160 && c.getBlue() <= 245) {
			return "poison";
		}
		
		/* 680, 78 / 806, 78 */
		// mana
		else if (c.getRed() < 35) {
			return "mana";
		}

		/* 1702, 560 */
		// noAttack
		else if (178 <= c.getRed() && c.getRed() <= 192 &&
				178 <= c.getGreen() && c.getGreen() <= 192 && 
				178 <= c.getBlue() && c.getBlue() <= 192) {
			return "noAttack";
		}
		
		/* 1168 648 */
		// noArrow
		// else if (c.getRed() == 128 && c.getGreen() == 128 && c.getBlue() == 127) {
		// return "noArrow";
		// }
		
		/* 1012 65 */
		else if (c.getRed() == 228 && c.getGreen() == 186 && c.getBlue() == 119) {
			return "yellow";
		}
		else if (c.getRed() == 161 && c.getGreen() == 239 && c.getBlue() == 115) {
			return "green";
		}
		

		
		return "nothing";
	}
	
	public String get1x1RGB2(int x, int y) {
		c = robot.getPixelColor(x, y);
//		System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
		/* 774, 69 */
		// hpforty
		if (c.getRed() >= 200 && c.getGreen() == 8 && c.getBlue() == 0) {
			return "hpforty";
		} 
		
		// else if (c.getRed() >= 140 && c.getGreen() == 19 && c.getBlue() == 15) {
		// return "hpforty";
		// }
		
		else if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() >= 200) {
			return "attack";
		}
		return "nothing";
	}

	/** Print warning message on console */
	public void printMessage(String message) {
		System.out.println("式式式式式式式式式式式式式式式式式式式");
		System.out.println(message);
		System.out.println("式式式式式式式式式式式式式式式式式式式");
		robot.delay(250);
	}
	
	/** Check warning sign on HP bar */
	 public boolean isPvp() {
	 sc.screenCapture(700, 56, 10, 1, "isPvp");
	 int isPvpValue = CalRGB.calcRGB("isPvp");
	
	 if (isPvpValue > 8 && isPvpValue < 11) {
	 return true;
	 }
	 return false;
	 }
	
	/** Check whether MP bar is blue to the left */
	// public boolean isPossibleTripleLeft() {
	// sc.screenCapture(680, 78, 1, 1, "isPossibleTripleLeft");// 680
	// int tripleValue = CalRGB.calcRGB("isPossibleTripleLeft");
	// System.out.println(tripleValue);
	// if (tripleValue == 16) { // 16 - full
	// return true;
	// }
	// return false;
	// }

	/** Check whether MP bar is blue to the right */
	// public boolean isPossibleTripleRight() {
	// sc.screenCapture(806, 78, 1, 1, "isPossibleTripleRight");// 806
	// int tripleValue = CalRGB.calcRGB("isPossibleTripleRight");
	// System.out.println(tripleValue);
	// if (tripleValue == 16) { // 16 - full
	// return true;
	// }
	// return false;
	// }
	
	/** Check whether weight bar is Blue 12, Orange 13, or Red 14 */
	// public int getWeightValue() {
	// sc.screenCapture(1585, 90, 1, 1, "getWeightValue");
	// int weightValue = CalRGB.calcRGB("getWeightValue");
	//
	// return weightValue; // 12 - b, 13 - o, 14 - r
	// }

	/** Check whether sward sign is orange */
	// public boolean isSward() {
	// sc.screenCapture(1702, 560, 1, 1, "isSward");
	// int swardValue = CalRGB.calcRGB("isSward");
	//
	// if (swardValue == 17) {// 17
	// return true;
	// }
	// return false;
	// }

	// 1589 688 -> 1560 670 - 255 255 255
	// public boolean equalsPortion() {
	// sc.screenCapture(1531, 637, 66, 66, "equalsPortion");
	// int equalsPortionValue = CalRGB.getEqualsValue("equalsPortion");
	//
	// if (equalsPortionValue == 4356) {
	// return true;
	// }
	// return false;
	// }
	
	/** Check whether setting sign is red */
	// public boolean isLetter() {
	// sc.screenCapture(1855, 65, 1, 1, "isLetter");
	// int letterValue = CalRGB.calcRGB("isLetter");
	//
	// if (letterValue == 15) { // 15
	// return true;
	// }
	// return false;
	// }

	/** Check whether letter sign is red */
	// public boolean chkLetter() {
	// sc.screenCapture(1628, 346, 1, 1, "chkLetter");
	// int letterValue = CalRGB.calcRGB("chkLetter");
	//
	// if (letterValue == 15) { // 15
	// return true;
	// }
	// return false;
	// }
	
	// public boolean equalsNPC() {
	// sc.screenCapture(1010, 230, 50, 50, "equalsNPC");
	// int equalsNPCValue = CalRGB.getEqualsValue("equalsNPC");
	//
	// if (equalsNPCValue >= 10) {
	// return true;
	// }
	// return false;
	// }

	// public boolean equalsDie() {
	// sc.screenCapture(1040, 190, 10, 10, "die");
	// int equalsDieValue = CalRGB.getEqualsValue("die");
	//
	// System.out.println(equalsDieValue);
	// return false;
	// }
}
