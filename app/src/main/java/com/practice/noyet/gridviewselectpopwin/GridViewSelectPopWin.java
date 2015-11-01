package com.practice.noyet.gridviewselectpopwin;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class GridViewSelectPopWin extends PopupWindow implements OnClickListener {
    private Activity mContext;
    private GridView mGridView;
    private onGridViewClickListener mConfirm;

    public GridViewSelectPopWin(Activity context) {
        super(context);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.include_select_gridview, null);
        view.findViewById(R.id.pop_cancel).setOnClickListener(this);
        view.findViewById(R.id.pop_confirm).setOnClickListener(this);
        setContentView(view);
        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0xe0000000));
        setFocusable(true);
        setAnimationStyle(R.style.popwin_anim_style);
        //弹出软键盘时，编辑框变位置时会有一个alpha动画
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(1f);
            }

        });

        mGridView = (GridView) view.findViewById(R.id.pop_gendi_gridview);
        init();
    }

    private void init() {


        mGridView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        // TODO Auto-generated method stub
        backgroundAlpha(0.5f);
        super.showAtLocation(parent, gravity, x, y);

    }

    public void showWin(View parent, int gravity, int x, int y,
                        onGridViewClickListener confirm,
                        GridViewSelectPopWinAdapter mAdapter) {
        this.showAtLocation(parent, gravity, x, y);
        this.mConfirm = confirm;
        mGridView.setAdapter(mAdapter);
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 0为不可见，1为透明
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.pop_cancel:
                dismiss();
                break;
            case R.id.pop_confirm:
                mConfirm.onClick();
                break;
            default:
                break;
        }
    }

    public interface onGridViewClickListener {
        void onClick();
    }

}
