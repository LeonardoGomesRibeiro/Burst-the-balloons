package br.com.burstballoon.sprites;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;
import br.com.burstballoon.config.ButtonDelegate;

public class Balloon extends CCLayer {

	private CCSprite buttonImage;
	private ButtonDelegate delegate;
	
	public Balloon(String image) {
		this.setIsTouchEnabled(true);
		this.buttonImage = CCSprite.sprite(image);
		addChild(buttonImage);
	}
	
	public void setTexture(CCTexture2D texture) {
		this.buttonImage.setTexture(texture);
	}

	public void setDelegate(ButtonDelegate delegate) {
		this.delegate = delegate;
	}
	
	@Override
	protected void registerWithTouchDispatcher() {
		CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, false);
	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		CGPoint touchLocation = CGPoint.make(event.getX(), event.getY());
		touchLocation = CCDirector.sharedDirector().convertToGL(touchLocation);
		touchLocation = this.convertToNodeSpace(touchLocation);
		
		// Verifica toque no botão
		if (CGRect.containsPoint(
			this.buttonImage.getBoundingBox(), touchLocation)) {
			delegate.buttonClicked(this);
			
			SoundEngine.sharedEngine().playEffect(
					CCDirector.sharedDirector().getActivity(), br.com.burstballoon.R.raw.balloon_bursted);
			SoundEngine.sharedEngine().pauseSound();
		}
		return true;
	}
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		
		return true;
	}

	
}
