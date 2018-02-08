package com.example.brenda.gokadatest.auth;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.brenda.gokadatest.R;
import com.example.brenda.gokadatest.databinding.ActivityAuthBinding;

/**
 * Created by brenda on 2/8/18.
 */

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";

    ActivityAuthBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        toolbar = binding.inclToolbar.toolbar;

        Fragment signInFragment = new SignInFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_container, signInFragment, "sign_in_frag");
        ft.commit();

    }
}
