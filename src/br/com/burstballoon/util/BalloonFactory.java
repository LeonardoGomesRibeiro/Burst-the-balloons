package br.com.burstballoon.util;

import br.com.burstballoon.sprites.Balloon;
import static br.com.burstballoon.config.Assets.*;
public class BalloonFactory {
	
	public static Balloon getBallon(int balloonNumber) {
		switch (balloonNumber) {
		case 1:
			return new Balloon(BALLOON_RED);
		case 2:
			return new Balloon(BALLOON_GREEN);
		case 3:
			return new Balloon(BALLOON_ORANGE);
		case 4:
			return new Balloon(BALLOON_BLUE);
		default:
			return new Balloon(BALLOON_RED);
		}
	}
}
