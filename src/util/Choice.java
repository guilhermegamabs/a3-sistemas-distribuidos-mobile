package util;

import java.io.Serializable;

public class Choice implements Serializable{
	private boolean playAlone;

	public Choice(boolean playAlone) {
		setPlayAlone(playAlone);
	}
	
	public boolean isPlayAlone() {
		return playAlone;
	}

	public void setPlayAlone(boolean playAlone) {
		this.playAlone = playAlone;
	}
	
	
}
