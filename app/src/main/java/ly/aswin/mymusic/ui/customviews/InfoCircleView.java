package ly.aswin.mymusic.ui.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import ly.aswin.mymusic.databinding.ViewInfoCircleBinding;
import ly.aswin.mymusic.ui.viewmodels.InfoCircleViewModel;

/**
 * Created by Aswin on 12-7-16
 *
 * InfoCircleView contains info in a circle and a label beneath the circle
 */
public class InfoCircleView extends RelativeLayout {

    private ViewInfoCircleBinding binding;

    public InfoCircleView(Context context) {
        super(context);
        init();
    }

    public InfoCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InfoCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfoCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setInfoCircleViewModel(InfoCircleViewModel model) {
        this.binding.setInfoCircleViewModel(model);
    }

    private void init() {
        this.binding = ViewInfoCircleBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

}
