package com.parsa.marketer.request;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsa.marketer.BaseFragment;
import com.parsa.marketer.MainActivity;
import com.parsa.marketer.R;
import com.parsa.marketer.database.model.Request;
import com.parsa.marketer.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import Utils.EdgeDecorator;
import Utils.FragmentUtil;
import Utils.UiUtil;

import static Utils.UiUtil.showSnack;


public class RequestListFragment extends BaseFragment {

    private View mRootView;

    private RecyclerView mRecycler;
    private RequestListAdapter mAdapter;
    FloatingActionButton mFabNew;

    public static RequestListFragment newInstance() {
        return new RequestListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_request_list, container, false);

        setToolbarTitle(getString(R.string.lable_request_list));
        initUI();

        return mRootView;

    }

    @Override
    public void onDestroyView() {
        setToolbarTitle("");
        super.onDestroyView();
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null)
            ((MainActivity) getActivity()).setToolbarTitle(title);

    }

    private void initUI() {
        mRecycler = mRootView.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RequestListAdapter(getContext(), getRequestList());
        mRecycler.setAdapter(mAdapter);
        mRecycler.setVisibility(View.VISIBLE);

        mRecycler.addItemDecoration(new EdgeDecorator(250));
        mFabNew = mRootView.findViewById(R.id.fab_new_request);
        mFabNew.setOnClickListener(view -> {
            if (getActivity() != null) {
                FragmentUtil.replaceFragment(
                        getActivity().getSupportFragmentManager(),
                        R.id.container_main,
                        NewRequestFragment.newInstance(),
                        NewRequestFragment.class.getName());
            } else {
                UiUtil.showSnack(mRootView, getString(R.string.null_context_error));
            }
        });

    }

    private List<Request> getRequestList() {
        List<Request> requestList = new ArrayList<>();
        {
            Request request = new Request("11111", "تهران", "علی علیان", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        {
            Request request = new Request("22222", "اصفهان", "قلی قلیان", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        {
            Request request = new Request("33333", "شیراز", "تقی تقیان", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        {
            Request request = new Request("44444", "رشت", "حسین حسینی", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        {
            Request request = new Request("55555", "مشهد", "امیر امیری", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        {
            Request request = new Request("66666", "کرمان", "یاور یاوری", "0083834001", "1397/5/17", "رضا رضایی");
            requestList.add(request);
        }

        return requestList;
    }


}
