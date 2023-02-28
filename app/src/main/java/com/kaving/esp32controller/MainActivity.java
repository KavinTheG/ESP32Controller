package com.kaving.esp32controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONObject;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Networking Configuration
        AndroidNetworking.initialize((getApplicationContext()));

        // Adding a Network Interceptor for Debugging purpose
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);

        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        // Declaring components on activity
        // joystick component
        JoystickView joystickView = findViewById(R.id.joystick);
        // Displays angle of joystick
        TextView angleText = findViewById(R.id.angle_text);
        // Displays strength of joystick
        TextView strengthText = findViewById(R.id.strength_text);

        joystickView.setOnMoveListener((angle, strength) -> {

            // Set the value of the TextViews
            angleText.setText("Angle: "  + angle);
            strengthText.setText("Strength: " + strength);

            // Send joystick data to the ESP32 endpoint
            AndroidNetworking.post("http://192.168.4.1/joystickdata?angle=" + angle +
                    "&strength=" + strength).build();

        });
    }
}