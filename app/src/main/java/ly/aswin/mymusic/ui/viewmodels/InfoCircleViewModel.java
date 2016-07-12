package ly.aswin.mymusic.ui.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ly.aswin.mymusic.BR;

/**
 * Created by Aswin on 12-7-16
 */
public class InfoCircleViewModel extends BaseObservable {

    @Bindable
    private String label;

    @Bindable
    private String info;

    public InfoCircleViewModel(String label, String info) {
        this.label = label;
        this.info = info;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        notifyPropertyChanged(BR.label);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        notifyPropertyChanged(BR.info);
    }
}
