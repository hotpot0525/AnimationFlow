package hotpot.game.animation;

import java.util.ArrayList;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AnimationFlow implements AnimationListener {

	private ArrayList<AnimationItem> animList;
	private int animationCount;
	private CallbackListener callbackListener;

	public AnimationFlow() {
		animList = new ArrayList<AnimationItem>();
		animationCount = 0;
	}

	public void add(View v, Animation anim) {
		animList.add(new AnimationItem(v, anim));
	}

	public void setCallback(CallbackListener callbackListener) {
		this.callbackListener = callbackListener;
	}

	private void countUp() {
		animationCount++;
	}

	public void start() {
		animationStart(0);
	}

	private void animationStart(int i) {
		if (animList.size() <= i) {
			animationEnd();
			return;
		}
		AnimationItem item = animList.get(i);

		setListener(item.animation);

		item.view.startAnimation(item.animation);
	}

	private void animationEnd() {
		if (callbackListener != null) {
			callbackListener.animationEnd();
		}
	}

	private void setListener(Animation animation) {
		animation.setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		countUp();
		animationStart(animationCount);
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
	}

	@Override
	public void onAnimationStart(Animation arg0) {
	}

	class AnimationItem {
		Animation animation;
		View view;

		public AnimationItem(View v, Animation anim) {
			view = v;
			animation = anim;
		}
	}

	public interface CallbackListener {
		void animationEnd();
	}
}
