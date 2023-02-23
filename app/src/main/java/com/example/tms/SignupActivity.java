package com.example.tms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tms.databinding.ActivitySignupBinding;

import java.util.ArrayList;
import java.util.Collections;

public class SignupActivity extends AppCompatActivity {

    private String tname,name,email,password;
    private ActivitySignupBinding binging;
    ArrayList<Integer> stdlist = new ArrayList<>();
    ArrayList<Integer> sublist = new ArrayList<>();
    boolean[] selectedStd;
    boolean[] selectedSub;
    String[] stdArray = {"1","2","3","4","5","6","7","8","9","10"};
    String[] subArray = {"Gujarati", "English", "Maths", "Science", "Social Science", "Hindi", "Environment","Sanskrit","Computer"};
    String finalSelectedStd = "";
    String finalSelectedSub = "";
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binging = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binging.getRoot());
        getSupportActionBar().hide();
        selectedStd = new boolean[stdArray.length];
        selectedSub = new boolean[subArray.length];
        // Show Preview of Std and Sub
        binging.btnPreviewSubStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalSelectedStd.equals("")||finalSelectedSub.equals("")){
                    Toast.makeText(SignupActivity.this, "Please Select Subjects And Standard", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog builder = new AlertDialog.Builder(SignupActivity.this).create();
                    builder.setTitle("Your Details");
                    builder.setMessage("Your Subjects :- \n" + finalSelectedSub + "\n\n" + "Your Standards :- \n" + finalSelectedStd);
                    builder.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        });
        // Show Dialog of Std
        binging.sStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("Select Standard");
                // set dialog non cancelable
                builder.setCancelable(false);
                builder.setMultiChoiceItems(stdArray, selectedStd, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            stdlist.add(i);
                            Collections.sort(stdlist);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            stdlist.remove(Integer.valueOf(i));
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < stdlist.size(); j++) {
                            // concat array value
                            stringBuilder.append(stdArray[stdlist.get(j)]);
                            // check condition
                            if (j != stdlist.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        binging.sStd.setText(stringBuilder.toString());
                        finalSelectedStd = stringBuilder.toString();
//                        Toast.makeText(SignupActivity.this, finalSelectedStd, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedStd.length; j++) {
                            // remove all selection
                            selectedStd[j] = false;
                            // clear language list
                            stdlist.clear();
                            // clear text view value
                            binging.sStd.setText("");
                            finalSelectedStd = "";
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
        // Show Dialog of Subjects
        binging.sSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                // set title
                builder.setTitle("Select Subjects");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(subArray, selectedSub, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            sublist.add(i);
                            // Sort array list
                            Collections.sort(sublist);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            sublist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < sublist.size(); j++) {
                            // concat array value
                            stringBuilder.append(subArray[sublist.get(j)]);
                            // check condition
                            if (j != sublist.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        binging.sSub.setText(stringBuilder.toString());
                        finalSelectedSub = stringBuilder.toString();
//                        Toast.makeText(SignupActivity.this, finalSelectedSub, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedSub.length; j++) {
                            // remove all selection
                            selectedSub[j] = false;
                            // clear language list
                            sublist.clear();
                            // clear text view value
                            binging.sSub.setText("");
                            finalSelectedSub = "";
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }
    public void toSignin(View v){
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }

    public void Signup(View v){
        tname = binging.edName.getText().toString();
        name = binging.edName.getText().toString();
        email = binging.edEmail.getText().toString();
        password = binging.edPassword.getText().toString();
        if(email.equals("") || password.equals("")||name.equals("") || tname.equals("") || finalSelectedSub.equals("")|| finalSelectedStd.equals("")){
            binging.edEmail.setError("This Field Required");
            binging.edPassword.setError("This Field Required");
            binging.edName.setError("This Field Required");
            binging.edClassesName.setError("This Field Required");
            Toast.makeText(this, "Please Select Subject And Standard", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }


}