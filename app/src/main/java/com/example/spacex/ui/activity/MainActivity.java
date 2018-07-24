package com.example.spacex.ui.activity;

import android.os.Bundle;

import com.example.spacex.R;
import com.example.spacex.di.component.ActivityComponent;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
