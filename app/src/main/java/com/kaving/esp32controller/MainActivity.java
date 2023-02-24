package com.kaving.esp32controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JoystickView joystickView = findViewById(R.id.joystick);

    }
}