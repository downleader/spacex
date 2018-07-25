package com.example.spacex.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.spacex.SpaceXApp;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.di.component.ApplicationComponent;
import com.example.spacex.di.component.DaggerActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = SpaceXApp.getInstance().getComponent();
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        inject(activityComponent);
    }

    protected void inject(ActivityComponent activityComponent) {
    }
}
