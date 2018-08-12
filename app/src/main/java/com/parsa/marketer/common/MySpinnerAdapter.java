package com.parsa.marketer.common;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsa.marketer.R;
import com.parsa.marketer.databinding.ListItemSpinnerBinding;

import java.util.ArrayList;
import java.util.List;

import Utils.StringUtil;
import Utils.UiUtil;
import Utils.Util;
import Utils.ValidationUtil;

import static Utils.UiUtil.showSnack;

public class MySpinnerAdapter extends RecyclerView.Adapter<MySpinnerAdapter.RequestVH> {
    private Context context;
    private List<SpinnerBottomSheetDialog.SpinnerItem> itemList;
    private ListItemSpinnerBinding binding;

    private SpinnerAdapterListener listener;

    public void setListener(SpinnerAdapterListener listener) {
        this.listener = listener;
    }

    public interface SpinnerAdapterListener {
        void onItemSelected(String SpinnerTitleStr);
    }

    public MySpinnerAdapter(Context context, List<SpinnerBottomSheetDialog.SpinnerItem> items) {
        this.context = context;
        this.itemList = items;
    }

    public void swapData(List<SpinnerBottomSheetDialog.SpinnerItem> items) {

        itemList = new ArrayList<>();
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = ListItemSpinnerBinding.inflate(layoutInflater, parent, false);
        return new RequestVH();
    }

    @Override
    public void onBindViewHolder(@NonNull RequestVH holder, int position) {
        SpinnerBottomSheetDialog.SpinnerItem item = itemList.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                UiUtil.setClickedTextAction(holder.mTvTitle,
                        ContextCompat.getColor(Util.getNotNullContext(context), R.color.colorPrimary),
                        ContextCompat.getColor(Util.getNotNullContext(context), R.color.text_sub));
            new Handler().postDelayed(() -> listener.onItemSelected(item.title), 150);
        });
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    class RequestVH extends RecyclerView.ViewHolder {

        TextView mTvTitle;

        RequestVH() {
            super(binding.getRoot());
            mTvTitle = binding.getRoot().findViewById(R.id.tv_item_title);
        }

        void bind(SpinnerBottomSheetDialog.SpinnerItem spinnerItem) {
            binding.setItem(spinnerItem);
            binding.executePendingBindings();

        }

    }

}

