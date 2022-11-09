package com.example.zaznoo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zaznoo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

import java.util.Objects;

public class TwitterFragment extends Fragment {

private FirebaseAuth firebaseAuth;
private Context contextNullSafe;

    public TwitterFragment() {
        // Required empty public constructor
    }


    public static TwitterFragment newInstance() {
        TwitterFragment fragment = new TwitterFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("step", "onAttach frement: " + context);
        contextNullSafe = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate: fragmeent");
        super.onCreate(savedInstanceState);
        Log.d("contextomri", "onCreate: " + contextNullSafe);
        FirebaseApp.initializeApp(contextNullSafe);
        firebaseAuth = FirebaseAuth.getInstance();
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");


        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        Log.d("TAG", "onCreate: pendingResultTask " + pendingResultTask);
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
//                                    startActivity(new Intent(TwitterActivity.this, MainActivity.class));
                                    Toast.makeText(contextNullSafe,"Logon successful", Toast.LENGTH_LONG );
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(contextNullSafe,"Error login", Toast.LENGTH_LONG );
                                    Log.d("onFailure", "onFailure: "+ e.toString());
                                }
                            });
        } else {
            firebaseAuth
                    .startActivityForSignInWithProvider(/* activity= */ getActivity(), provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
//                                    startActivity(new Intent(TwitterActivity.this, MainActivity.class));
                                    Toast.makeText(contextNullSafe,"Logon successful", Toast.LENGTH_LONG );

                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: pendingResultTask " + e.toString());
                                    Toast.makeText(contextNullSafe,"Error login", Toast.LENGTH_LONG );
                                    Log.d("onFailure", "onFailure: "+ e.toString());
                                }
                            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter, container, false);
    }
}