package com.example.finalcriminalintent.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.finalcriminalintent.controller.fragment.CrimeListFragment;
import com.example.finalcriminalintent.controller.fragment.LoginFragment;


public class CrimeListActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_REPOSITORY = "ExtraCrimeRepository";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CrimeListActivity.class);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}