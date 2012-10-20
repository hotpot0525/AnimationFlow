package hotpot.game.animation;

import android.app.Activity;
import android.os.Bundle;
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
        
        AnimationFlow animationFlow = new AnimationFlow();
        animationFlow.add(view1, anim1);
        animationFlow.add(view2, anim2);
        animationFlow.add(view1, anim3);
        animationFlow.start();
        
    }
}