package hotpot.game.animation;

import hotpot.game.animation.AnimationFlow.AnimationItem;
import hotpot.game.animation.AnimationFlow.CallbackListener;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationFlowActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        
        TranslateAnimation anim1 = new TranslateAnimation(1, 100, 1, 100);
        anim1.setDuration(5000);
        TranslateAnimation anim2 = new TranslateAnimation(100, 1, 100, 1);
        anim2.setDuration(5000);
        ScaleAnimation anim3 = new ScaleAnimation(1, 2, 1, 2);
        anim3.setDuration(5000);
        
        
        AnimationItem item = new AnimationItem(view1, anim1);
        AnimationItem item2 = new AnimationItem(view2, anim2);
        ArrayList<AnimationItem> animationItemArrayList = new ArrayList<AnimationFlow.AnimationItem>();
        animationItemArrayList.add(item);
        animationItemArrayList.add(item2);
        
        
        AnimationFlow animationFlow = new AnimationFlow();
        animationFlow.add(view1, anim1);
        animationFlow.add(view2, anim2);
        animationFlow.add(view1, anim3);
        animationFlow.add(animationItemArrayList);
        animationFlow.setCallback(new CallbackListener() {
			
			@Override
			public void animationEnd() {
				// hogehoge
				Log.i("AnimationFlowActivity","animationFlow end");
			}
		});
        animationFlow.start();
        
    }
}