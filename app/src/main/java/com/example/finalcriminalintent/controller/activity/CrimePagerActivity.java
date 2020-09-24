package com.example.finalcriminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.controller.fragment.CrimeDetailFragment;
import com.example.finalcriminalintent.model.Crime;
import com.example.finalcriminalintent.repository.CrimeDBRepository;
import com.example.finalcriminalintent.repository.IRepository;
import com.example.finalcriminalintent.ZoomOutPageTransformer;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    public static final String EXTRA_CRIME_ID = "com.example.criminalintent.crimeId";
    public static final String TAG = "CPA";
    private IRepository mRepository;
    private UUID mCrimeId;

    //private ViewPager2 mViewPagerCrimes;
    private ViewPager mViewPagerCrimes;



    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mRepository = CrimeDBRepository.getInstance(this);
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        findViews();
        initViews();
    }

    private void findViews() {
        mViewPagerCrimes = findViewById(R.id.view_pager_crimes);
        // ask
        mViewPagerCrimes.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    private void initViews() {
        List<Crime> crimes = mRepository.getCrimes();
        CrimePagerAdapter adapter = new CrimePagerAdapter(getSupportFragmentManager(), crimes);
        mViewPagerCrimes.setAdapter(adapter);

        int currentIndex = mRepository.getPosition(mCrimeId);
        mViewPagerCrimes.setCurrentItem(currentIndex);

    }

    private class CrimePagerAdapter extends FragmentStatePagerAdapter {

        private List<Crime> mCrimes;

        public CrimePagerAdapter(@NonNull FragmentManager fm,List<Crime> crimes) {
            super(fm);
            mCrimes = crimes;
        }



        public List<Crime> getCrimes() {
            return mCrimes;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "position: " + (position + 1));
//
            Crime crime = mCrimes.get(position);
            CrimeDetailFragment crimeDetailFragment =
                    CrimeDetailFragment.newInstance(crime.getId());

            return crimeDetailFragment;
        }

        @Override
        public int getCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }

    }
}