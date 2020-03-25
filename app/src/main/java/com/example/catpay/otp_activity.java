package com.example.catpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;

public class otp_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    String mVerificationId;
    String code;
    EditText editTextOTP ;
    Button generateOTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_otp);

        editTextOTP = findViewById(R.id.et_otp);
        generateOTP = findViewById(R.id.continuebtn);

        Bundle b = getIntent().getExtras();
        assert b != null;
        mVerificationId = b.getString("mVerificationId");
        String mResendToken = b.getString("mResendToken");
        assert mVerificationId != null;
        mAuth = FirebaseAuth.getInstance();

        generateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextOTP.getText().toString();

                System.out.println("mVerificationId" + mVerificationId);
                System.out.println("code" + code);

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("onComplete");

                            FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();
                            Intent i = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                            startActivity(i);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                System.out.println("The verification code entered was invalid");

                            }
                        }
                    }
                });
    }
}
