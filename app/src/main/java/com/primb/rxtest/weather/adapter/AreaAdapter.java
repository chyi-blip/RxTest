package com.primb.rxtest.weather.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primb.rxtest.R;
import com.primb.rxtest.databinding.ItemAreaBinding;
import com.primb.rxtest.weather.module.CityBean;
import com.primb.rxtest.weather.module.ProvinceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * datebinding的recycleview适配器
 */

public class AreaAdapter<T> extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private OnRecyclerViewClickListener<T> listener;
    @NonNull
    private List<T> mDatas = new ArrayList<>();

    public AreaAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(mDatas.get(position));
        //点击事件的处理
        holder.bind.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClickListener(v, mDatas.get(position));
            }
        });
    }

    public void setListener(OnRecyclerViewClickListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder<T> extends RecyclerView.ViewHolder {

        public ItemAreaBinding bind;

        public ViewHolder(View itemView) {
            super(itemView);
            bind = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull T t) {
            if (t instanceof ProvinceBean) {
                bind.setIsProvince(true);
                bind.setProvince((ProvinceBean) t);
            } else {
                bind.setIsProvince(false);
                bind.setCity((CityBean) t);
            }

        }
    }

    public interface OnRecyclerViewClickListener<T> {
        void OnItemClickListener(View view, T t);
    }
}
