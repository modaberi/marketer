package com.parsa.marketer.request;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsa.marketer.BaseFragment;
import com.parsa.marketer.R;
import com.parsa.marketer.common.EnumSpinnerType;
import com.parsa.marketer.common.SpinnerBottomSheetDialog;
import com.parsa.marketer.database.model.Request;
import com.parsa.marketer.databinding.FragmentNewRequestBinding;

import java.util.List;

import Utils.DataUtil;
import Utils.UiUtil;
import Utils.ValidationUtil;
import customwidget.CustomSpinner;


public class NewRequestFragment extends BaseFragment {

    private View mRootView;

    FragmentNewRequestBinding binding;

    public static NewRequestFragment newInstance() {
        return new NewRequestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_request, container, false);
        mRootView = binding.getRoot();
        binding.setRequest(new Request());

        setupListeners();

        return mRootView;

    }

    private void setupListeners() {

        binding.btnAddNewRequest.setOnClickListener(v -> {
            if (hasValidInputs()) {
                UiUtil.showSnack(mRootView, getString(R.string.status_check_message));
            }
        });

        binding.spinnerState.setOnClickListener(v -> setUpDialog(EnumSpinnerType.STATE,binding.spinnerState,"dialog_spinner_state"));

        binding.spinnerCity.setOnClickListener(v -> setUpDialog(EnumSpinnerType.CITY,binding.spinnerCity,"dialog_spinner_city", binding.spinnerState.getSelectedItem()));

        binding.spinnerActivityType.setOnClickListener(v -> setUpDialog(EnumSpinnerType.ACTIVITY_TYPE,binding.spinnerActivityType,"dialog_spinner_activity_type"));

        binding.spinnerOwnershipType.setOnClickListener(v -> setUpDialog(EnumSpinnerType.OWNERSHIP_TYPE,binding.spinnerOwnershipType,"dialog_spinner_ownership_type"));
    }

    private void setUpDialog(EnumSpinnerType spinnerType, CustomSpinner spinner, String tag) {
        SpinnerBottomSheetDialog dialog = SpinnerBottomSheetDialog.newInstance(spinnerType, getSpinnerList(spinnerType, null ));
        dialog.setListener(spinner::setSelectedItem);
        dialog.show(getActivity().getSupportFragmentManager(), tag);
        dialog.setCancelable(true);
    }

    private void setUpDialog(EnumSpinnerType spinnerType, CustomSpinner spinner, String tag, String selectedState) {
        SpinnerBottomSheetDialog dialog = SpinnerBottomSheetDialog.newInstance(spinnerType, getSpinnerList(spinnerType, selectedState ));
        dialog.setListener(spinner::setSelectedItem);
        dialog.show(getActivity().getSupportFragmentManager(), tag);
        dialog.setCancelable(true);
    }

    private List<String> getSpinnerList(EnumSpinnerType spinnerType, String selectedState) {
        if (spinnerType.equals(EnumSpinnerType.STATE)){
            return DataUtil.getStateList();
        }else  if (spinnerType.equals(EnumSpinnerType.CITY)){
            return DataUtil.getCityList(selectedState);
        }else if (spinnerType.equals(EnumSpinnerType.ACTIVITY_TYPE)){
            return DataUtil.getActivityTypeList();
        }else if (spinnerType.equals(EnumSpinnerType.OWNERSHIP_TYPE)){
            return DataUtil.getOwnershipTypeList();
        }else return null;
    }


    private boolean hasValidInputs() {
        return ValidationUtil.isValidString(getContext(), binding.etHoseName)
                && ValidationUtil.isValidSSN(getContext(), binding.etHostSsn)
                && ValidationUtil.isValidPhone(getContext(), binding.etHostPhone)
                && ValidationUtil.notNullOrEmpty(binding.spinnerState.getSelectedItem())
                && ValidationUtil.notNullOrEmpty(binding.spinnerCity.getSelectedItem())
                && ValidationUtil.notNullOrEmpty(binding.etAddress.getText().toString())
                && ValidationUtil.notNullOrEmpty(binding.etZipCode.getText().toString());
    }


}
