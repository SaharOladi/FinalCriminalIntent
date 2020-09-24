package com.example.finalcriminalintent.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalcriminalintent.R;
import com.example.finalcriminalintent.controller.activity.CrimeDetailActivity;
import com.example.finalcriminalintent.controller.activity.CrimeListActivity;
import com.example.finalcriminalintent.model.User;
import com.example.finalcriminalintent.repository.UserDBRepository;

import java.util.UUID;


public class LoginFragment extends Fragment {


    static final int REQUEST_CODE_SIGN_UP = 0;
    private static final String TAG_SIGN_UP = "signUpFragment";
    public static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    private User mUser;

    private UserDBRepository mRepository;
    private EditText mUsername, mPassword;
    private Button mLogin;
    private TextView mCreateAccount;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initUI(view);

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                SingUpFragment signUpFragment = SingUpFragment.newInstance(username, password);
                signUpFragment.setTargetFragment(LoginFragment.this, REQUEST_CODE_SIGN_UP);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(
                        R.id.fragment_container, signUpFragment, TAG_SIGN_UP).commit();

            }

        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRepository.checkUserLogin(mUsername.toString(), mPassword.toString())) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(
                            R.id.fragment_container, new CrimeListFragment(), TAG_LIST_FRAGMENT).commit();

                }
            }


        });

        return view;
    }

    private void initUI(View view) {
        mPassword = view.findViewById(R.id.txt_password);
        mUsername = view.findViewById(R.id.txt_username);
        mLogin = view.findViewById(R.id.btn_login);
        mCreateAccount = view.findViewById(R.id.btn_sign_up);
        mRepository = UserDBRepository.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_CANCELED || data == null)
            return;

        if (requestCode == REQUEST_CODE_SIGN_UP) {

            User user = new User();

            mUsername.setText(user.getUserName());
            mPassword.setText(user.getUserPassword());

        }
    }
}