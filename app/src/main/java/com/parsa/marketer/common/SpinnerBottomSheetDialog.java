package com.parsa.marketer.common;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsa.marketer.R;
import com.parsa.marketer.database.model.Request;

import java.util.ArrayList;
import java.util.List;

public class SpinnerBottomSheetDialog extends BottomSheetDialogFragment implements MySpinnerAdapter.SpinnerAdapterListener {

    private static EnumSpinnerType spinnerType;
    private static List<String> itemList;

    private SpinnerListener listener;

    public void setListener(SpinnerListener listener) {
        this.listener = listener;
    }

    public interface SpinnerListener {
        void onItemSelected(String title);
    }

    public static SpinnerBottomSheetDialog newInstance(EnumSpinnerType type, List<String> spinnerItemList) {
        SpinnerBottomSheetDialog fragment = new SpinnerBottomSheetDialog();
        spinnerType = type;
        itemList = spinnerItemList;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.layout_bottomsheet_spinner, container, false);

        TextView mTvTitle = mRootView.findViewById(R.id.tv_spinner_header);
        mTvTitle.setText(setSpinnerType(spinnerType));

        ImageView mImageClose = mRootView.findViewById(R.id.btn_close_dialog);
        mImageClose.setOnClickListener(v -> dismiss());

        RecyclerView mRecycler = mRootView.findViewById(R.id.recycler_bbottoomsheet_spinner);

        MySpinnerAdapter mAdapter = new MySpinnerAdapter(getContext(), getSpinnerItemList(itemList));
        mAdapter.setListener(this);
        mRecycler.setAdapter(mAdapter);

        TextInputEditText mEtSearch = mRootView.findViewById(R.id.et_search);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> searchedList = new ArrayList<>();
                new Handler().postDelayed(() -> {
                    for (String str : itemList) {
                        if (str.contains(mEtSearch.getText().toString())) {
                            searchedList.add(str);
                        }
                    }
                    MySpinnerAdapter mAdapter = new MySpinnerAdapter(getContext(), getSpinnerItemList(searchedList));
                    mAdapter.setListener(SpinnerBottomSheetDialog.this);
                    mRecycler.setAdapter(mAdapter);
//                    mAdapter.swapData(getSpinnerItemList(searchedList));

                }, 100);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return mRootView;
    }

    private String setSpinnerType(EnumSpinnerType spinnerType) {
        if (spinnerType.name().equals(EnumSpinnerType.STATE.methodName)) {
            return getString(R.string.hint_state);
        } else if (spinnerType.name().equals(EnumSpinnerType.CITY.methodName)) {
            return getString(R.string.hint_city);
        } else if (spinnerType.name().equals(EnumSpinnerType.ACTIVITY_TYPE.methodName)) {
            return getString(R.string.hint_activity_type);
        } else if (spinnerType.name().equals(EnumSpinnerType.OWNERSHIP_TYPE.methodName)) {
            return getString(R.string.hint_ownership_type);
        } else return "انتخاب کنید...";
    }

    private List<SpinnerItem> getSpinnerItemList(List<String> itemList) {
        int count = 0;
        List<SpinnerItem> spinnerItemList = new ArrayList<>();
        for (String string : itemList) {
            spinnerItemList.add(new SpinnerItem(string, count + 1));
        }

        return spinnerItemList;
    }

    @Override
    public void onItemSelected(String spinnerTitleStr) {
        if (listener != null) {
            listener.onItemSelected(spinnerTitleStr);
            dismiss();
        }
    }

    public class SpinnerItem {
        public String title;
        public int id;

        public SpinnerItem(String title, int id) {
            this.title = title;
            this.id = id;
        }
    }

}
