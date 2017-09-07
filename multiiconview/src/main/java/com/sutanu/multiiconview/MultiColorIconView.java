package com.sutanu.multiiconview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by sutanurath on 07/09/17.
 */

public class MultiColorIconView extends ImageView {

    private int mColor;
    private int mViewDuration;
    private int mViewAnimationType;
    private Bitmap mFinalBitmap;

    public static final int NONE = 0;
    public static final int FADE_IN = 1;
    public static final int FADE_OUT = 2;
    public static final int ROTATE = 3;
    public static final int SCALE = 4;

    public MultiColorIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiColorIconView);
        mColor = a.getColor(R.styleable.MultiColorIconView_multiColorIconViewColor, 0xAAFFFFFF);
        mViewDuration = a.getColor(R.styleable.MultiColorIconView_viewAnimationDuration, 0);
        mViewAnimationType = a.getInt(R.styleable.MultiColorIconView_animationType, NONE);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap sourceBitmap = convertDrawableToBitmap(drawable);
        mFinalBitmap = changeImageColor(sourceBitmap, mColor);

        canvas.drawBitmap(mFinalBitmap, 0, 0, null);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Start the animation
        startAnimation(mViewDuration);
    }

    public void setMultiColorIconViewColor(int color) {
        if (mColor != color) {
            mColor = color;
            invalidate();
        }
    }

    public void setViewDuration(int duration) {
        if (mViewDuration != duration) {
            mViewDuration = duration;
            invalidate();
        }
    }

    private static Bitmap changeImageColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }

    private static Drawable covertBitmapToDrawable(Context context, Bitmap bitmap) {
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }

    private static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void setAnimationType(int type){

        if (mViewAnimationType != type) {
            switch (type) {
                case NONE:
                    mViewAnimationType = NONE;
                    break;
                case FADE_IN:
                    mViewAnimationType = FADE_IN;
                    break;
                case FADE_OUT:
                    mViewAnimationType = FADE_OUT;
                    break;
                case ROTATE:
                    mViewAnimationType = ROTATE;
                    break;
                case SCALE:
                    mViewAnimationType = SCALE;
                    break;
            }
            startAnimation(mViewDuration);
            invalidate();
        }
    }

    private void startAnimation(int duration) {
        clearAnimation();

        switch (mViewAnimationType) {
            case NONE:

                break;
            case FADE_IN:

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(duration);
                this.setAnimation(fadeIn);

                break;
            case FADE_OUT:
                Animation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setInterpolator(new AccelerateInterpolator());
                fadeOut.setStartOffset(0);
                fadeOut.setDuration(duration);
                this.setAnimation(fadeOut);
                break;
            case ROTATE:

                RotateAnimation rotate = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF,
                        0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(duration);
                this.setAnimation(rotate);
                break;
            case SCALE:

                final float growTo = 1.2f;

                ScaleAnimation grow = new ScaleAnimation(1, growTo, 1, growTo,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                grow.setDuration(duration / 2);
                ScaleAnimation shrink = new ScaleAnimation(growTo, 1, growTo, 1,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                shrink.setDuration(duration / 2);
                shrink.setStartOffset(duration / 2);
                AnimationSet growAndShrink = new AnimationSet(true);
                growAndShrink.setInterpolator(new LinearInterpolator());
                growAndShrink.addAnimation(grow);
                growAndShrink.addAnimation(shrink);
                this.setAnimation(growAndShrink);

                break;

        }
        invalidate();

    }

}