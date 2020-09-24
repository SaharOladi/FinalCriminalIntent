package com.example.finalcriminalintent.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.model.User;
import com.example.finalcriminalintent.repository.UserDBRepository;

import java.io.File;
import java.util.List;


public class SingUpFragment extends Fragment {

    private static final String ARGS_USERNAME = "args_username";
    private static final String ARGS_PASSWORD = "args_password";
    public static final String TAG_LOG_IN = "TAG_LOG_IN";
    private User mUser = new User();
    private EditText mUsername, mPassword;
    private Button mSignUp;

    private UserDBRepository mRepository;


    public static SingUpFragment newInstance(String username, String password) {

        Bundle args = new Bundle();

        SingUpFragment fragment = new SingUpFragment();
        args.putSerializable(ARGS_USERNAME, username);
        args.putSerializable(ARGS_PASSWORD, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser.setUserName(getArguments().getString(ARGS_USERNAME));
        mUser.setUserPassword(getArguments().getString(ARGS_PASSWORD));


    }

    public SingUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sing_up, container, false);

        initUI(view);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUsername())
                    if (validatePassword())
                        sendResult();
            }
        });

        return view;
    }


    private void initUI(View view) {
        mUsername = view.findViewById(R.id.txt_username_second);
        mPassword = view.findViewById(R.id.txt_password_second);
        mSignUp = view.findViewById(R.id.btn_sign_up_second);
        mRepository = UserDBRepository.getInstance(getActivity().getApplicationContext());

        mUsername.setText(mUser.getUserName());
        mPassword.setText(mUser.getUserPassword());
    }

    private void sendResult() {

        mUser.setUserName(mUsername.getText().toString());
        mUser.setUserPassword(mPassword.getText().toString());
        mRepository.insertUser(mUser);
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(LoginFragment.REQUEST_CODE_SIGN_UP, Activity.RESULT_OK, intent);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(SingUpFragment.this).commit();
        fragmentManager.beginTransaction().replace(
                R.id.fragment_container, new LoginFragment(), TAG_LOG_IN).commit();
    }

    private boolean validateUsername() {

        String username = mUsername.getText().toString().trim();
        if (username.isEmpty())
            return false;

        return true;

    }

    private boolean validatePassword() {

        String password = mPassword.getText().toString().trim();
        if (password.isEmpty())
            return false;

        return true;

    }
}
