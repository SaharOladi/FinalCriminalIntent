package com.example.finalcriminalintent.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.finalcriminalintent.controller.fragment.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        return new LoginFragment();
    }
}