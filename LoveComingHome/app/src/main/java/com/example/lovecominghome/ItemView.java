package com.example.lovecominghome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.example.lovecominghome.ConvertUtils;



public class ItemView extends LinearLayout {
    private ImageView mIcon;
    private TextView mTitle;
    private ImageView mArrow;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        // 获取从xml文件中设置的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemView, defStyleAttr, 0);
        try {
            mTitle.setText(array.getString(R.styleable.ItemView_text));
            mTitle.setTextColor(array.getColor(R.styleable.ItemView_textColor, Color.BLACK));
            mIcon.setImageResource(array.getResourceId(R.styleable.ItemView_icon, R.mipmap.ic_launcher));
            mArrow.setImageResource(R.mipmap.ic_list_right_arrow);
            if (array.getBoolean(R.styleable.ItemView_hiddenIcon, false)) {
                mIcon.setVisibility(GONE);
                LayoutParams params = (LayoutParams) mTitle.getLayoutParams();
                params.setMargins((int) ConvertUtils.dp2px(20), 0, 0, 0);
                mTitle.setLayoutParams(params);
            }
        } finally {
            array.recycle();
        }
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setBackgroundColor(Color.parseColor("#FFFFFF"));

        mIcon = new ImageView(context);
        mTitle = new TextView(context);
        mArrow = new ImageView(context);
        addView(mIcon);
        addView(mTitle);
        addView(mArrow);

        LayoutParams iconParams = (LayoutParams) mIcon.getLayoutParams();
        iconParams.width = ConvertUtils.dp2px(20);
        iconParams.height = ConvertUtils.dp2px(24);
        iconParams.setMargins(ConvertUtils.dp2px(20), 0, 0, 0);
        mIcon.setLayoutParams(iconParams);

        LayoutParams textParams = (LayoutParams) mTitle.getLayoutParams();
        textParams.width = ConvertUtils.dp2px(0);
        textParams.height = ConvertUtils.dp2px(24);
        textParams.weight = 1;
        textParams.setMargins(ConvertUtils.dp2px(6), 0, 0, 0);
        mTitle.setLayoutParams(textParams);
        mTitle.setTextSize(20);

        LayoutParams arrowParams = (LayoutParams) mArrow.getLayoutParams();
        arrowParams.width = ConvertUtils.dp2px(16);
        arrowParams.height = ConvertUtils.dp2px(16);
        arrowParams.setMargins(0, 0, ConvertUtils.dp2px(16), 0);
        mArrow.setLayoutParams(arrowParams);
    }
}
