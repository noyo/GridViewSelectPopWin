package com.practice.noyet.gridviewselectpopwin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noyet on 2015/10/16.
 */
public class GridViewSelectPopWinAdapter extends BaseAdapter {

    private Context mContext;
    private int mWidth;
    //存储每个item的选中状态
    private List<Boolean> index;
    //当前被选中的item位置
    private int selectIndex;
    private List<Integer> mList;

    public int getSelectIndex() {
        return selectIndex;
    }

    public List<Boolean> getIndex() {
        return index;
    }


    public GridViewSelectPopWinAdapter(Context context, int width, List<Integer> list) {
        mContext = context;
        mWidth = width;
        this.mList = list;
        index = new ArrayList<>();
        selectIndex = -1;
        for (int i = 0; i < mList.size(); i++) {
            index.add(false);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view != null) {
            holder = (Holder) view.getTag(R.id.tag_data);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.pop_win_gridview_item, null);
            holder = new Holder();
            holder.mItem = (RelativeLayout) view.findViewById(R.id.pop_win_gridview_item);
            float scale = mContext.getResources().getDisplayMetrics().density;
            holder.mItem.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (50 * scale + 0.5F)));
            holder.mImageView = (TextView) view.findViewById(R.id.pop_gridview_item_iv);
            holder.mTextView = (TextView) view.findViewById(R.id.pop_gridview_item_tv);
            view.setTag(R.id.tag_data, holder);
            view.setTag(R.id.tag_id, position);
        }
        holder.mTextView.setText(mList.get(position) + "");
        view.setOnClickListener(listener);
        if (index.get(position)) {
            setSelectIndex(holder, true);
        } else {
            setSelectIndex(holder, false);
        }
        return view;
    }

    private void setSelectIndex(Holder holder, boolean isSelect) {
        if (isSelect) {
            holder.mItem.setSelected(true);
            holder.mImageView.setVisibility(View.VISIBLE);
            holder.mTextView.setTextColor(mContext.getResources().getColor(android.R.color.black));
        } else {
            holder.mItem.setSelected(false);
            holder.mImageView.setVisibility(View.GONE);
            holder.mTextView.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Holder holder = (Holder) view.getTag(R.id.tag_data);
            int position = (Integer) view.getTag(R.id.tag_id);
            if (!index.get(position)) {
                index.set(position, true);
                if (selectIndex == -1) {
                    setSelectIndex(holder, true);
                } else {
                    index.set(selectIndex, false);
                    notifyDataSetChanged();
                }
                selectIndex = position;
            } else {
                index.set(position, false);
                notifyDataSetChanged();
                selectIndex = -1;
            }
        }
    };

    static class Holder {
        RelativeLayout mItem;
        TextView mImageView;
        TextView mTextView;
    }
}
