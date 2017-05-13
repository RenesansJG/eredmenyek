package hu.renesans.eredmenyek.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.IOException;
import java.io.InputStream;


public class AssetHelper {
    public static byte[] readContent(Context context, String fileName) throws IOException {
        InputStream is = null;

        try {
            is = context.getAssets().open(fileName);
            byte[] content = new byte[is.available()];
            //noinspection ResultOfMethodCallIgnored
            is.read(content);
            return content;
        } finally {
            if (is != null) is.close();
        }
    }

    public static void loadImage(String path, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(Uri.parse("file:///android_asset/" + path))
                .into(imageView);
    }

    public static void loadImage(String path, Context context, Callback<Drawable> callback) {
        Glide.with(context).load(Uri.parse("file:///android_asset/" + path))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        callback.call(resource);
                    }
                });
    }

    public interface Callback<T> {
        void call(T t);
    }
}
