package com.example.finalcriminalintent.controller.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.controller.activity.CrimeListActivity;
import com.example.finalcriminalintent.model.Crime;
import com.example.finalcriminalintent.repository.CrimeDBRepository;
import com.example.finalcriminalintent.repository.IRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class CrimeDetailFragment extends Fragment {

    public static final String TAG = "CDF";
    public static final String ARGS_CRIME_ID = "crimeId";
    public static final String FRAGMENT_TAG_DATE_PICKER = "DatePicker";
    public static final String TIMER_DIALOG_FRAGMENT_TAG = "DialogTimer";
    public static final int TIME_PICKER_REQUEST_CODE = 1;
    public static final int REQUEST_CODE_DATE_PICKER = 0;

    private EditText mEditTextTitle;
    private Button mButtonDate;
    private Button mButtonTime;
    private CheckBox mCheckBoxSolved;

    private Button mButtonNext;
    private Button mButtonLast;
    private Button mButtonPrevious;
    private Button mButtonFirst;

    private Crime mCrime;
    private IRepository mRepository;

    public static CrimeDetailFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_ID, crimeId);

        CrimeDetailFragment fragment = new CrimeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CrimeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Log.d(TAG, "onCreate");

        mRepository = CrimeDBRepository.getInstance(getActivity());

        //this is storage of this fragment
        UUID crimeId = (UUID) getArguments().getSerializable(ARGS_CRIME_ID);
        mCrime = mRepository.getCrime(crimeId);
    }

    /**
     * 1. Inflate the layout (or create layout in code)
     * 2. find all views
     * 3. logic for all views (like setListeners)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_detail, container, false);

        findViews(view);
        initViews();
        setListeners();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
//        updateCrime();

        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, "onDetach");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            //get response from intent extra, which is user selected date
            Date userSelectedDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);
            mCrime.setDate(userSelectedDate);
            mButtonDate.setText( new SimpleDateFormat("MM/dd/yyyy").format(mCrime.getDate()));

            updateCrime();
        }
        if(requestCode == TIME_PICKER_REQUEST_CODE){
            Date userSelectedDate  = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_USER_SELECTED_TIME);
            mCrime.setDate(userSelectedDate);
            mButtonTime.setText(new SimpleDateFormat("HH:mm:ss").format(mCrime.getDate()));

            updateCrime();
        }
    }


    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.crime_title);
        mButtonDate = view.findViewById(R.id.crime_date);
        mButtonTime = view.findViewById(R.id.crime_time);
        mCheckBoxSolved = view.findViewById(R.id.crime_solved);

        mButtonFirst = view.findViewById(R.id.btn_first);
        mButtonLast = view.findViewById(R.id.btn_last);
        mButtonNext = view.findViewById(R.id.btn_next);
        mButtonPrevious = view.findViewById(R.id.btn_previous);
    }

    private void initViews() {
        mEditTextTitle.setText(mCrime.getTitle());
        mCheckBoxSolved.setChecked(mCrime.isSolved());
        mButtonDate.setText(mCrime.getDate().toString());
//        mButtonDate.setEnabled(false);
    }

    private void setListeners() {
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s + ", " + start + ", " + before + ", " + count);

                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCheckBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment =
                        DatePickerFragment.newInstance(mCrime.getDate());

                //create parent-child relations between CDF and DPF
                datePickerFragment.setTargetFragment(
                        CrimeDetailFragment.this,
                        REQUEST_CODE_DATE_PICKER);

                datePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DATE_PICKER);
            }
        });

        mButtonTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mCrime.getDate());
                timePickerFragment.setTargetFragment(CrimeDetailFragment.this,TIME_PICKER_REQUEST_CODE);
                timePickerFragment.show(getFragmentManager(), TIMER_DIALOG_FRAGMENT_TAG);

            }
        });


        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNext()) {
                    next();
                }
                initViews();
            }
        });

        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkPrevious()) {
                    previous();
                }
                initViews();
            }
        });

        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID btnId = mRepository.getCrimes().get(0).getId();
                mCrime = mRepository.getCrime(btnId);
                initViews();
            }
        });

        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID btnId = mRepository.getCrimes().get(mRepository.getCrimes().size() - 1).getId();
                mCrime = mRepository.getCrime(btnId);
                initViews();
            }
        });
    }


    private void next() {
        for (int i = 0; i < mRepository.getCrimes().size(); i++) {
            if (mCrime.equals(mRepository.getCrimes().get(i))) {
                UUID btnId = mRepository.getCrimes().get(i + 1).getId();
                mCrime = mRepository.getCrime(btnId);
                break;
            }
        }
    }

    private void previous() {
        for (int i = 0; i < mRepository.getCrimes().size(); i++) {
            if (mCrime.equals(mRepository.getCrimes().get(i))) {
                UUID btnId = mRepository.getCrimes().get(i - 1).getId();
                mCrime = mRepository.getCrime(btnId);
                break;
            }
        }
    }

    private boolean checkNext() {
        if (mCrime.equals(mRepository.getCrimes().get(mRepository.getCrimes().size() - 1))) {
            UUID btnId = mRepository.getCrimes().get(0).getId();
            mCrime = mRepository.getCrime(btnId);
            return true;
        }

        return false;
    }

    private boolean checkPrevious() {
        if (mCrime.equals(mRepository.getCrimes().get(0))) {
            UUID btnId = mRepository.getCrimes().get(mRepository.getCrimes().size() - 1).getId();
            mCrime = mRepository.getCrime(btnId);
            return true;
        }

        return false;
    }

    private void updateCrime() {
        mRepository.updateCrime(mCrime);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crime_detail_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_crime_detail_delete_item:

                mRepository.deleteCrime(mCrime);
                Intent intent = CrimeListActivity.newIntent(getActivity());
                startActivity(intent);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}