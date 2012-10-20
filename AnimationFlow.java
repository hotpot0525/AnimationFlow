package hotpot.game.animation;

import java.util.ArrayList;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AnimationFlow implements AnimationListener {

	ArrayList<AnimationItem> animList;
	int animationCount;

	public AnimationFlow() {
		animList = new ArrayList<AnimationFlow.AnimationItem>();
		animationCount = 0;
	}

	public void add(View v, Animation anim) {
		animList.add(new AnimationItem(v, anim));
	}

	public void start() {
		animationStart(0);

	}

	private void animationStart(int i) {
		if(animList.size() < i){
			return;
		}
		AnimationItem item = animList.get(i);
		
		if(animList.size()-1 != i){
			setListemer(item.animation);
		}
		
		item.view.startAnimation(item.animation);
		
	}

	private void setListemer(Animation animation) {
		animation.setAnimationListener(this);
	}

	class AnimationItem {
		Animation animation;
		View view;

		public AnimationItem(View v, Animation anim) {
			view = v;
			animation = anim;
		}
	}

	private void countUp() {
		if (animationCount < animList.size()-1) {
			animationCount++;
		}
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		countUp();
		animationStart(animationCount);

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
	}

	@Override
	public void onAnimationStart(Animation arg0) {

	}

}
