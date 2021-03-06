package ly.aswin.mymusic.utils;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ly.aswin.mymusic.R;

/**
 * Created by Aswin on 10-7-2016
 */
public class BindingAdapters {

    /**
     * Bind image (by XML files) without any placeholder or fallback image
     */
    @BindingAdapter("bind:imageUrlSimple")
    public static void loadSimpleImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    /**
     * Bind image (by XML files) with placeholder and fallback image
     */
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).into(imageView);
    }
}