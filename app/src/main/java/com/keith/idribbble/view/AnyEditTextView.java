package com.keith.idribbble.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.keith.idribbble.R;

public class AnyEditTextView extends EditText {

    public AnyEditTextView(Context context){
        super(context);
    }

    public AnyEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()){
            setTypefaceFromAttrs(context, attrs);
        }
    }

    public AnyEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()){
            setTypefaceFromAttrs(context, attrs);
        }
    }

    private void setTypefaceFromAttrs(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.AnyTextView);
        String typefaceName = values.getString(R.styleable.AnyTextView_typeface);

        setTypeface(typefaceName);
    }

    public void setTypeface(String typefaceName){
        if (AnyTextViewCache.typefaceCache.containsKey(typefaceName)){
            setTypeface(AnyTextViewCache.typefaceCache.get(typefaceName));
        } else {
            Typeface typeface;

            try {
                typeface = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/" + typefaceName);
            } catch (Exception e){
                Log.v("AnyTextView", "Typeface " + typefaceName + " not found, or could not be loaded. " +
                        "Showing default typeface in AnyEditTextView.");
                return;
            }

            AnyTextViewCache.typefaceCache.put(typefaceName, typeface);
            setTypeface(typeface);
        }
    }
}
