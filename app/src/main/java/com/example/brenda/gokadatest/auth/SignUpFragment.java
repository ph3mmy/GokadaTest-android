package com.example.brenda.gokadatest.auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brenda.gokadatest.R;
import com.example.brenda.gokadatest.databinding.FragmentSignUpBinding;

/**
 * Created by brenda on 2/8/18.
 */

public class SignUpFragment extends Fragment {

    private static final String TAG = "SignUpFragment";

    FragmentSignUpBinding binding;
    View view;

    // required empty constructor
    public SignUpFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        view = binding.getRoot();

        return view;
    }
}
