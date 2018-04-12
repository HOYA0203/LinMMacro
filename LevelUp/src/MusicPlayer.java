import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	public void playMusic(String fileName) {
		fileName = "music/" + fileName + ".mp3";
		try {
			Player p = new Player(new FileInputStream(fileName));
			p.play();
			p.close();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			System.err.println("MusicPlayer : playMusic : JavaLayerException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("MusicPlayer : playMusic : FileNotFoundException");
		}

	}
}
