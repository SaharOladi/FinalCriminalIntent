package com.example.finalcriminalintent.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.controller.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}