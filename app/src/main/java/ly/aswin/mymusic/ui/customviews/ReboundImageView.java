package ly.aswin.mymusic.ui.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

/**
 * Created by Aswin on 10-7-2016
 */
public class ReboundImageView extends ImageView {

    private OnClickListener onImageClickListener;

    private static final int TENSION = 400;
    private static final int DAMPER = 20;
    private static final float UNSELECTED = 0f;
    private static final float SELECTED = 1f;
    private static final float SCALE = 0.2f;

    public ReboundImageView(Context context) {
        super(context);
        init();
    }

    public ReboundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReboundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReboundImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.onImageClickListener = l;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init() {
        final Spring spring = SpringSystem.create().createSpring();
        spring.setSpringConfig(new SpringConfig(TENSION, DAMPER));
        spring.addListener(new SpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float val = (float) spring.getCurrentValue();
                float scale = SELECTED - (val * SCALE);
                ReboundImageView.this.setScaleX(scale);
                ReboundImageView.this.setScaleY(scale);
            }

            @Override
            public void onSpringAtRest(Spring spring) {

            }

            @Override
            public void onSpringActivate(Spring spring) {

            }

            @Override
            public void onSpringEndStateChange(Spring spring) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(SELECTED);
                        return true;
                    case MotionEvent.ACTION_UP:
                        onClickWithDelay();
                        spring.setEndValue(UNSELECTED);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        spring.setEndValue(UNSELECTED);
                        return true;
                }

                return false;
            }
        });
    }

    private void onClickWithDelay() {
        if (onImageClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onImageClickListener.onClick(ReboundImageView.this);
                }
            }, 125);
        }
    }
}
