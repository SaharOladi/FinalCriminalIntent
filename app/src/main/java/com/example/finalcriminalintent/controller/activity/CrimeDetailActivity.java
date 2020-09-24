package com.example.finalcriminalintent.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.controller.fragment.CrimeDetailFragment;

public class CrimeDetailActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CrimeDetailActivity.class);

        return intent;
    }

    @Override
    public Fragment createFragment() {
        return new CrimeDetailFragment();
    }
}