package hotpot.game.animation;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AnimationFlow implements AnimationListener {

	private ArrayList<AnimationList> animList2;
	private ArrayList<AnimationItem> animList;
	private int animationCount;
	private CallbackListener callbackListener;

	public AnimationFlow() {
		// animList = new ArrayList<AnimationItem>();
		animationCount = 0;

		animList2 = new ArrayList<AnimationFlow.AnimationList>();

	}

	public void add(View v, Animation anim) {
		// animList.add(new AnimationItem(v, anim));

		AnimationList list = new AnimationList();
		list.add(new AnimationItem(v, anim));
		animList2.add(list);
	}
	
	public void add(ArrayList<AnimationItem> animationItemArrayList){
		AnimationList list = new AnimationList();
		for(AnimationItem item : animationItemArrayList){
			list.add(item);
		}
		animList2.add(list);
	}
	
//	public void add(AnimationList animationList){
//		animList2.add(animationList);
//	}
//	public AnimationList getAnimationList(){
//		return animList2.get(0);
//	}

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
		// if (animList.size() <= i) {
		if (animList2.size() <= i) {
			animationEnd();
			return;
		}
		// AnimationItem item = animList.get(i);

		{
			int longDurationItemId = 0;
			for(int j=0; j < animList2.get(i).list.size(); j++){
				AnimationItem item = animList2.get(i).list.get(j);
				
				if(animList2.get(i).list.get(longDurationItemId).animation.getDuration() < item.animation.getDuration()) {
					longDurationItemId = j;
				}
			}
			setListener(animList2.get(i).list.get(longDurationItemId).animation);
			
		}
//			setListener(item.animation);
//		item.view.startAnimation(item.animation);
		for(int j=0; j < animList2.get(i).list.size(); j++){
			AnimationItem item = animList2.get(i).list.get(j);
			item.view.startAnimation(item.animation);
		}
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

	public static class AnimationItem {
		Animation animation;
		View view;

		public AnimationItem(View v, Animation anim) {
			view = v;
			animation = anim;
		}
	}

	class AnimationList {
		ArrayList<AnimationItem> list;

		public AnimationList() {
			list = new ArrayList<AnimationFlow.AnimationItem>();
		}

		public void add(AnimationItem item) {
			list.add(item);
		}
	}

	public interface CallbackListener {
		void animationEnd();
	}
}
