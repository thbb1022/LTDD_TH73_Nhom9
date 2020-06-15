package com.example.docsach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.docsach.R.id.txt_password;

public class LoginFragment extends Fragment {

    private Context mContext;
    EditText txtEmail, txtPassword;
    Button btnLogin;
    private FirebaseAuth firebaseAuth;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment,
                container, false);

        final Button button = (Button) view.findViewById(R.id.btn_Register);
        txtEmail = (EditText)view.findViewById(R.id.txt_email);
        txtPassword = (EditText)view.findViewById(txt_password);
        btnLogin = (Button)view.findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(mContext, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(mContext, "Password To Short", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(mContext, MainActivity.class));


                                } else {
                                    Toast.makeText(mContext, "Login Faile", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}