package br.com.burstballoon.scenes;

import static br.com.burstballoon.config.DeviceSettings.screenHeight;
import static br.com.burstballoon.config.DeviceSettings.screenResolution;
import static br.com.burstballoon.config.DeviceSettings.screenWidth;

import java.util.Random;

import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;

import br.com.burstballoon.config.ButtonDelegate;
import br.com.burstballoon.screens.ScreenBackground;
import br.com.burstballoon.sprites.Balloon;
import br.com.burstballoon.util.BalloonFactory;
import static br.com.burstballoon.config.Assets.BACKGROUND;
public class GameScene extends CCLayer implements ButtonDelegate {

	private ScreenBackground background;

	private Balloon balloon, sender;
	CCTextureCache cache = CCTextureCache.sharedTextureCache();
	
	public static CCScene createGame() {

		// Create Scene
		GameScene layer = new GameScene();
		CCScene scene = CCScene.node();
		scene.addChild(layer);

		return scene;
	}
	
	
	public GameScene() {
		this.background = new ScreenBackground(BACKGROUND);
		this.background.setPosition(screenResolution(CGPoint.ccp(
				screenWidth() / 2.0f, screenHeight() / 2.0f)));
		
		this.addChild(this.background);
		
		schedule("showBalloons", 1.0f);
	}
	
	public void showBalloons(float f) {
		Random random = new Random();
		
		int balloonNumber = (int) 1 + random.nextInt(4);		
		balloon = BalloonFactory.getBallon(balloonNumber);
		balloon.setDelegate(this);
		
		int xPosition = (int) 0 + random.nextInt((int)screenWidth());
		int minY = (int) (balloon.getContentSize().height / 2.0f) + 77;
		int actualY = random.nextInt((int) screenWidth()) + minY;
		
		
		// Create the target slightly off-screen along the right edge,
				// and along a random position along the Y axis as calculated above
				// positioning the object on center of screen and on top.
		balloon.setPosition(xPosition, 0);
		addChild(balloon);

		// Determine speed of the target
		int actualDuration = 4;

		// Create the actions
		CCMoveTo actionMove = CCMoveTo.action(actualDuration,
				CGPoint.ccp(xPosition, +actualY));
		CCSequence actions = CCSequence.actions(actionMove);
		balloon.runAction(actions);
	}


	@Override
	public void buttonClicked(Balloon sender) {
		sender.setTexture(cache.addImage("bursted_ballon_red.png"));
		sender.removeSelf();
	}
	
}
