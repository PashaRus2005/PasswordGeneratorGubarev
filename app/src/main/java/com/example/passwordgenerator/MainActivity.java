package com.example.passwordgenerator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    private TextView passwordTextView;
    private SeekBar passwordLengthSeekBar;
    private TextView passwordLengthTextView;
    private CheckBox uppercaseCheckBox, lowercaseCheckBox, numbersCheckBox, specialCharsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordTextView = findViewById(R.id.passwordTextView);
        Button generateButton = findViewById(R.id.generateButton);
        passwordLengthSeekBar = findViewById(R.id.passwordLengthSeekBar);
        passwordLengthTextView = findViewById(R.id.passwordLengthTextView);
        uppercaseCheckBox = findViewById(R.id.uppercaseCheckBox);
        lowercaseCheckBox = findViewById(R.id.lowercaseCheckBox);
        numbersCheckBox = findViewById(R.id.numbersCheckBox);
        specialCharsCheckBox = findViewById(R.id.specialCharsCheckBox);

        passwordLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int passwordLength = progress + 6;
                passwordLengthTextView.setText(String.valueOf(passwordLength));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        generateButton.setOnClickListener(v -> {
            int passwordLength = passwordLengthSeekBar.getProgress() + 6;
            boolean useUppercase = uppercaseCheckBox.isChecked();
            boolean useLowercase = lowercaseCheckBox.isChecked();
            boolean useNumbers = numbersCheckBox.isChecked();
            boolean useSpecialChars = specialCharsCheckBox.isChecked();

            String password = generatePassword(passwordLength, useUppercase, useLowercase, useNumbers, useSpecialChars);
            passwordTextView.setText(password);
        });
    }

    private String generatePassword(int length, boolean useUppercase, boolean useLowercase, boolean useNumbers, boolean useSpecialChars) {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";

        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        StringBuilder passwordBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int charType = random.nextInt(4);
            switch (charType) {
                case 0:
                    if (useUppercase) {
                        passwordBuilder.append(upperChars.charAt(random.nextInt(upperChars.length())));
                    } else {
                        i--;
                    }
                    break;
                case 1:
                    if (useLowercase) {
                        passwordBuilder.append(lowerChars.charAt(random.nextInt(lowerChars.length())));
                    } else {
                        i--;
                    }
                    break;
                case 2:
                    if (useNumbers) {
                        passwordBuilder.append(numberChars.charAt(random.nextInt(numberChars.length())));
                    } else {
                        i--;
                    }
                    break;
                case 3:
                    if (useSpecialChars) {
                        passwordBuilder.append(specialChars.charAt(random.nextInt(specialChars.length())));
                    } else {
                        i--;
                    }
                    break;
            }
        }

        return passwordBuilder.toString();
    }
}