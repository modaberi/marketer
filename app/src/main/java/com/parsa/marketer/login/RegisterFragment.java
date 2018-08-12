package com.parsa.marketer.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsa.marketer.BaseFragment;
import com.parsa.marketer.MainActivity;
import com.parsa.marketer.R;
import com.parsa.marketer.database.model.User;
import com.parsa.marketer.databinding.FragmentRegisterBinding;

import Utils.FragmentUtil;
import Utils.UiUtil;
import Utils.UiUtil.DetailsTransition;
import Utils.ValidationUtil;


public class RegisterFragment extends BaseFragment {

    private View mRootView;

    private TextInputEditText mEtUsername;
    private TextInputEditText mEtPassword;
    private TextInputEditText mEtPhone;
    private TextInputEditText mEtEmail;
    private TextInputEditText mEtSSN;
    FragmentRegisterBinding binding;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        mRootView = binding.getRoot();
        binding.setUser(new User(0,"","","","",""));

        initUI();

        setupListeners();

        return mRootView;

    }

    private void setupListeners() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasValidInputs() && getActivity() != null) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment fragment = LoginFragment.newInstance();

                if (getActivity() != null) {

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
                } else UiUtil.showSnack(mRootView, getString(R.string.null_context_error));
            }
        });

    }

    private boolean hasValidInputs() {
        return ValidationUtil.isValidString(getContext(), mEtUsername)
                && ValidationUtil.isValidPassword(getContext(), mEtPassword)
                && ValidationUtil.isValidSSN(getContext(), mEtSSN)
                && ValidationUtil.isValidPhone(getContext(), mEtPhone)
                && ValidationUtil.isValidEmail(getContext(), mEtEmail);
    }

    private void initUI() {
        mEtUsername = binding.etUsername;
        mEtPassword = binding.etPassword;
        mEtEmail = binding.etEmail;
        mEtSSN = binding.etSsn;
        mEtPhone = binding.etPhone;
    }
}
