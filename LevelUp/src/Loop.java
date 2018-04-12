import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
//75 11/16
//77 12/6 17h
//78 12/31 22h
//79 1/26 1h
//80 2/21 20h
public class Loop {
	static int TIMEOUT = 80000;
	// 2h - 15824 1h 8640 8137
	// 3h - 23736
	// 4h - 31648
	
	static int[] macro = {145, 180, 215, 250, 285, 320, 355, 390, 425, 460};
	//----------------------
//	static int BONE = macro[0];
	static int CENTER = macro[0];
	static int EAST = macro[1];
	static int SAM = macro[2];
	//----------------------
	static int MIRROR = macro[3];
	static int CIENAGA = macro[4];
	//----------------------
	static int TRIBLUE = macro[5];
	static int SOULTT = macro[6];
	static int SOULT = macro[7];
	static int SOUL = macro[8];
	// ----------------------
	static int levUpCnt = 0;
	
	public void forLoop() throws AWTException {
		Robot robot = new Robot();
		SimpleDateFormat dayTime = new SimpleDateFormat("hh-mm-ss");
		
		SubController sub = new SubController();
		ScreenCapture sc = new ScreenCapture();
		MouseController mc = new MouseController();
		MusicPlayer mp = new MusicPlayer();

		int[] arrVal = { 45, 42, 45, 44, 39, 40, 47 };
		/********************************************/
		boolean bKakao = false; // true-out, false-in
		boolean bDungeon = true;// true-tel, false-belue
		boolean bDragonField = false;// true-o, false-x
		boolean bSubClient = false;
		boolean bSwapArrow = false;
		boolean bOman = false;
		/********************************************/
		boolean portion_100 = false;
		boolean portion_0 = false;

		int ssIsPvp = 1; // pvp screenshot
		int ssNoAttack = 1; // azitt screenshot
		int ssPortion = 1; // 0 portion screenshot
		int ssSafeZone = 1; // safe zone screenshot
		int ssNewLetter = 1; // letter screenshot
		
		int letterTime = 0; // letter time exclude 5, 6, 7
		int cntNoAttack = 0;

		String wghVal; // check weight 12 - b, 13 - o, 14 - r
		String left;
		String right;

		boolean stt = false;
		boolean st = false;
		boolean tb = false;
		
		long startTime, endTime, lTime, sumTime = 0;
		String timeStamp;

		System.out.println("==================================");
		System.out.println("\tWelcome");
		System.out.println("==================================");

//		robot.delay(1000);
//		for (int i = 0; i < 10; i++) {
//			sub.test(686, 181);
//			if (sub.get1x1RGB(1673, 646) == "noArrow")
//				System.out.println("qqq");
//			robot.delay(500);
//		}

		robot.delay(1000);
		for (int t = 1; t < 90000; t++) {
			timeStamp = timeStamp();
			startTime = System.currentTimeMillis();

			// 700, 56, 10, 1
			/** pvp */
			if (!bDragonField) {
				if (sub.getNxNRGB(700, 56, 7, 1) == "pvp") {
					sub.printMessage("isPvp!");
					sc.fullScreenCapture("isPvp/[" + timeStamp + "] ", ssIsPvp++);
					if (bDungeon) {
						mc.teleport();
					} else {
						if (sub.get1x1RGB2(790, 69) != "hpforty") {
							robot.delay(arrVal[t % 7] * 20);
							mc.beleu();
							mp.playMusic("lineage");
							levUp(mc, EAST, EAST, EAST, EAST, EAST, EAST);
							mc.sendMessage("[" + timeStamp + "] isPvp", bKakao);
							levUp(mc, CENTER, CENTER, CENTER, CENTER, CENTER, CENTER);

						}
					}
				}
			}
			
			/** Sub client **/
			if (bSubClient) {
				if (t % 5 == 0) {
					if (sub.getNxNRGB(1418, 802, 7, 1) == "pvp") {
						robot.delay(2000);
						mc.mouseMove(1688, 1043);
					}
				}
			}
			
			/** Dragon Field */
			if(bDragonField) {
				if(sub.get1x1RGB2(790, 69) != "hpforty") {
					sub.printMessage("DragonFieldPvp!");
					sub.test(790, 69);
					sc.fullScreenCapture("isPvp/[" + timeStamp + "] DF ", ssIsPvp++);
					mc.beleu();
					mp.playMusic("CountingStars");
					mc.sendMessage("[" + timeStamp + "] isPvp", bKakao);
					levUp(mc, EAST, SAM, CENTER, CIENAGA, EAST, CIENAGA);
				} // eof pvp
				
				if (t % 20 == 0) {
					if (sub.get1x1RGB2(1702, 560) != "attack") {
						sub.printMessage("noAttack??");
						for (int i = 0; i < 41; i++) {
							if (sub.get1x1RGB2(1702, 560) != "attack") {
								cntNoAttack++;
								robot.delay(500);
							} else {
								break;
							}
						}
						// +++++ belue +++++
						if (cntNoAttack > 40) {
							sub.printMessage("No Attack Belue!!");
							sc.fullScreenCapture("NoAttack/[" + timeStamp + "] ", ssNoAttack++);
							/*********/
							mc.beleu();
							/*********/
							mp.playMusic("CountingStars");
							mc.sendMessage("[" + timeStamp + "] Azitt", bKakao);
							levUp(mc, EAST, SAM, CENTER, CIENAGA, EAST, CIENAGA);
						}
						cntNoAttack = 0; // @@ initialize
					}
				} // eof no attack
				
				// if (t % 20 == 0) {
				// if (sub.get1x1RGB(1585, 90) == "blue" && tb == false) {
				// mc.mouseMove(TRIBLUE);
				// tb = true;
				// }
				// }
			} // eof dragon field
			
			/** noAttack */
			if (bDungeon) {
				if (t % 10 == 0) {
					if (sub.get1x1RGB(1695, 540) == "noAttack") {
						sub.printMessage("noAttack?");
						// +++++ Dungeon +++++
						for (int i = 0; i < 5; i++) {
							if (sub.get1x1RGB(1695, 540) == "noAttack") {
								cntNoAttack++;
								robot.delay(500);
							} else {
								break;
							}
						}
						// +++++ teleport +++++
						if (cntNoAttack > 4) {
							sub.printMessage("Teleport!");
							/************/
							mc.teleport();
							/************/
						}
						cntNoAttack = 0; // @@ initialize
					} // eof (check noAttack?)
				} // eof (t % 10)
			} // eof dungeon

			/** Portion **/
			if (t % 20 == 0) {
				/** Check 0 portion **/
				if (!portion_0) {
					if (sub.get1x1RGB(1560, 670) == "noPortion") {
						sub.printMessage("Portion__0");
						sc.fullScreenCapture("Portion/[" + timeStamp + "] ", ssPortion++);
						mc.clickAuto();
						while (true) {
							if (sub.get1x1RGB(1695, 540) == "noAttack") {
								robot.delay(500);
								mc.clickF4();
								robot.delay(4000);
								break;
							}
						}
						/*********/
						mc.beleu();
						portion_0 = true; // @@ initialize
						/*********/
						mp.playMusic("Stay");
						mc.sendMessage("[" + timeStamp + "] NoPortion", bKakao);
						break;
					}
				} // eof portion
				
				/** Check 0 Arrow -> Swap Arrow **/
				if (sub.get1x1RGB(1673, 646) == "noArrow") {
					if (!bSwapArrow) {
						mc.swapArrow();
						bSwapArrow = true;
					}
				} // eof noArrow
				
				/** Check 100 Portion **/
//				if (!bDragonField && !bDungeon && !portion_100) {
//					if (sub.getNxNRGB(1574, 692, 1, 9) == "portion") {
//						sub.printMessage("Portion__100");
//						sc.fullScreenCapture("Portion/[" + timeStamp + "] ", ssPortion++);
//						/*********/
//						mc.beleu();
//						portion_100 = true; // @@ initialize
//						/*********/
//						mp.playMusic("TheSpectre");
//						mc.sendMessage("[" + timeStamp + "] looPortion", bKakao);
//						levUp(mc, CIENAGA, CIENAGA, CIENAGA, CIENAGA, CIENAGA, CIENAGA);
//					}
//				} // eof portion 100
			} // eof 20
			
			if (bOman) {
				if (t % 3 == 0) {
					sub.test(686, 181);
					if (sub.get1x1RGB(686, 181) == "poison") {
						mc.teleport();
					}
				}
			}
			
			/** SafeZone */
			if (t % 5 == 0 && t % 10 != 0) {
				// blue(for bu ddang)
				if(sub.get1x1RGB(1806, 360) == "safe") {
					// ma
					if (sub.getNxNRGB(1797, 325, 1, 5) == "safe") {
						sub.printMessage("SafeZone");
						sc.fullScreenCapture("SafeZone/[" + timeStamp + "] ", ssSafeZone++);
						/*********/
						mc.beleu();
						/*********/
//						if (bDungeon) {
//							mc.mannerMode();
//							bDungeon = false; // @@ initialize
//						}
						mp.playMusic("BlowYourMind");
						levUp(mc, EAST, EAST, EAST, EAST, EAST, EAST);
						mp.playMusic("BlowYourMind");
						mc.sendMessage("[" + timeStamp + "] SafeZone", bKakao);
						levUp(mc, CENTER, CENTER, CENTER, CENTER, CENTER, CENTER);
					}
				}
			}
			
			/** letter */
			if (t % 500 == 0) {
				if (sub.get1x1RGB(1855, 65) == "new") {
					letterTime = Integer.parseInt(timeStamp.substring(0, 2));
					if (letterTime != 5 || letterTime != 6 || letterTime != 7) {
						mc.openOption();
						robot.delay(arrVal[t % 7] * 15);
						if (sub.get1x1RGB(1627, 346) == "new") {
							mc.readLetter();
							robot.delay(arrVal[t % 7] * 20);
							sub.printMessage("newLetter");
							sc.fullScreenCapture("newLetter/[" + timeStamp + "] ", ssNewLetter++);
							mc.closeOption();
							mc.sendMessage("[" + timeStamp + "] NewLetter", bKakao);
						}
						mc.openOption();
					}
				}
			} // eof 57

			/** Automatic hunting */
//			if (t % 40 == 0) {
				// if (bDungeon) {
				/* 680, 78 / 806, 78 */
//				left = sub.get1x1RGB(680, 78);
//				right = sub.get1x1RGB(806, 78);
//				wghVal = sub.get1x1RGB(1585, 90);

				// if (wghVal == "orange") {
				// if (right == "mana") {
				// if (st == true) {
				// mc.mouseMove(SOULT);
				// st = false;
				// robot.delay(1000);
				// }
				// if (stt == false) {
				// robot.delay(500);
				// mc.mouseMove(SOULTT);
				// stt = true;
				// }
				// }
				//
				// if (left != "mana") {
				// if (stt == true) {
				// mc.mouseMove(SOULTT);
				// stt = false;
				// robot.delay(1000);
				// }
				// if (st == false) {
				// robot.delay(500);
				// mc.mouseMove(SOULT);
				// st = true;
				// }
				// }
				// }

//				if (wghVal == "blue" && tb == false) {
					// if (stt == true) {
					// mc.mouseMove(SOULTT);
					// stt = false;
					// robot.delay(500);
					// }
					// if (st == true) {
					// mc.mouseMove(SOULT);
					// st = false;
					// robot.delay(500);
					// }
//					mc.mouseMove(TRIBLUE);
//					tb = true;
//				}
				// }
//			} // eof auto hunting
		
			/** TIME OUT **/
			if (t == TIMEOUT) {
				sub.printMessage("TIMEOUT");
				sc.fullScreenCapture("TIMEOUT/[" + timeStamp + "] ", t);
				mc.beleu();
				mp.playMusic("BlowYourMind");
				mc.sendMessage("[" + timeStamp + "] time out", bKakao);
				levUp(mc, EAST, SAM, CENTER, EAST, SAM, CENTER);
			}

			if (ssIsPvp == 10) {
				sub.printMessage("DANGER");
				sc.fullScreenCapture("DANGER/[" + timeStamp + "] ", ssIsPvp);
				mc.beleu();
				mp.playMusic("NewRules");
				mc.sendMessage("[" + timeStamp + "] danger", bKakao);
				break;
			}

			robot.delay(200 + arrVal[t % 7]);

			endTime = System.currentTimeMillis();
			lTime = endTime - startTime;
			sumTime += lTime;
			
			System.out.println("TIME : " + lTime + "(ms) " + dayTime.format(new Date(sumTime)));
			System.out.println("\n\t[[[S  A  F  E]]]\t" + t);
			System.out.println("==================================");
		}
	}
	
	public static void levUp(MouseController mc, int v1, int v2, int v3, int v4, int v5, int v6) {
		int[] arr = {v1, v4, v2, v5, v3, v6};
		
		mc.findLevUp(arr[levUpCnt%6]);
		levUpCnt++;
	}
	
	public static String timeStamp() {
		SimpleDateFormat dayTime = new SimpleDateFormat("hh-mm-ss");

		long time = System.currentTimeMillis();
		String str = dayTime.format(new Date(time));

		return str;
	}
}