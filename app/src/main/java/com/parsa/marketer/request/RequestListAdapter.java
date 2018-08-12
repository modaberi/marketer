package com.parsa.marketer.request;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsa.marketer.R;
import com.parsa.marketer.database.model.Request;
import com.parsa.marketer.databinding.ListItemRequestBinding;

import java.util.List;

import Utils.UiUtil;
import Utils.Util;

import static Utils.UiUtil.showSnack;


public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.RequestVH> {
    private Context context;
    private List<Request> requestList;
    private ListItemRequestBinding binding;

    public RequestListAdapter(Context context, List<Request> requestList) {
        this.context = context;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public RequestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = ListItemRequestBinding.inflate(layoutInflater, parent, false);
        return new RequestVH();
    }

    @Override
    public void onBindViewHolder(@NonNull RequestVH holder, int position) {
        Request request = requestList.get(position);
        holder.bind(request);
    }

    @Override
    public int getItemCount() {
        return requestList != null ? requestList.size() : 0;
    }

    public class RequestVH extends RecyclerView.ViewHolder {

        RequestVH() {
            super(binding.getRoot());
        }

        TextView mTvCalcel;
        TextView mTvSend;

        void bind(Request request) {
            binding.setRequest(request);
            binding.executePendingBindings();

            mTvCalcel = binding.getRoot().findViewById(R.id.tv_cancel);
            mTvSend = binding.getRoot().findViewById(R.id.tv_send);

            mTvCalcel.setOnClickListener(v -> onCancelRequest(mTvCalcel));

            mTvSend.setOnClickListener(v -> onSendRequest(mTvSend));
        }

        void onCancelRequest(TextView textView) {

            UiUtil.setClickedTextAction(textView,
                    ContextCompat.getColor(Util.getNotNullContext(context), R.color.color_red_button_pressed),
                    ContextCompat.getColor(Util.getNotNullContext(context), R.color.color_red_button)
            );

            new Handler().postDelayed(new AlertDialog.Builder(context)
                    .setMessage(context.getString(R.string.cancel_request))
                    .setPositiveButton(context.getString(R.string.yes_message), (dialog, which) ->
                            UiUtil.showSnack(context, context.getString(R.string.status_check_message)))
                    .setNegativeButton(context.getString(R.string.no_message), null)::show, 150);

        }

        void onSendRequest(TextView textView) {
            UiUtil.setClickedTextAction(textView,
                    ContextCompat.getColor(Util.getNotNullContext(context), R.color.color_green_button_pressed),
                    ContextCompat.getColor(Util.getNotNullContext(context), R.color.color_green_button)
            );

            new Handler().postDelayed(() -> UiUtil.showSnack(context, context.getString(R.string.status_check_message)), 150);
        }
    }

}

