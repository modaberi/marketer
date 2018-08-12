package com.parsa.marketer.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.transition.Fade;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsa.marketer.BaseFragment;
import com.parsa.marketer.MainActivity;
import com.parsa.marketer.R;
import com.parsa.marketer.database.model.User;
import com.parsa.marketer.databinding.FragmentLoginBinding;

import Utils.FragmentUtil;
import Utils.UiUtil;
import Utils.Util;
import Utils.ValidationUtil;

import static Utils.UiUtil.*;


public class LoginFragment extends BaseFragment {

    private View mRootView;
    private TextInputEditText mEtUsername;
    private TextInputEditText mEtPassword;
    private TextView mTvForgotPassword;

    private FragmentLoginBinding binding;
    public static LoginFragment newInstance() {
      return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mRootView = binding.getRoot();
        binding.setUser(new User(0,"","","","",""));

        initUI();

        setupListeners();

        return mRootView;

    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            if (hasValidInputs() && getActivity() != null) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        binding.tvRegister.setOnClickListener(v -> {
            if (getActivity() != null) {
                RegisterFragment fragment = RegisterFragment.newInstance();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    fragment.setSharedElementEnterTransition(new DetailsTransition());
                    fragment.setEnterTransition(new Fade());
                    setExitTransition(new Fade());
                    fragment.setSharedElementReturnTransition(new DetailsTransition());
                }

                FragmentUtil.replaceFragmentWithTransition(
                        getActivity().getSupportFragmentManager(),
                        fragment,
                        R.id.container_login_register,
                        fragment.getClass().getName(),
                        binding.logo,
                        getString(R.string.logoTransitionName));
            }else showSnack(mRootView, getString(R.string.null_context_error));
        });

        mTvForgotPassword.setOnClickListener(v -> {

            UiUtil.setClickedTextAction(mTvForgotPassword,
                    ContextCompat.getColor(Util.getNotNullContext(getContext()), R.color.colorPrimary),
                    ContextCompat.getColor(Util.getNotNullContext(getContext()), R.color.text_sub)
                    );

            new Handler().postDelayed(() -> showSnack(mRootView, getString(R.string.status_check_message)), 150);

        });
    }

    private boolean hasValidInputs() {
        return ValidationUtil.isValidString(getContext(), mEtUsername)
                && ValidationUtil.isValidPassword(getContext(), mEtPassword);
    }

    private void initUI() {

        mEtUsername = binding.etUsername;
        mEtPassword = binding.etPassword;
        mTvForgotPassword = binding.tvForgotPassword;

    }
}
