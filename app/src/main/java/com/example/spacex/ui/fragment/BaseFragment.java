package com.example.spacex.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.spacex.SpaceXApp;
import com.example.spacex.di.component.ApplicationComponent;
import com.example.spacex.di.component.DaggerFragmentComponent;
import com.example.spacex.di.component.FragmentComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = SpaceXApp.getInstance().getComponent();
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        inject(fragmentComponent);
    }

    protected void inject(FragmentComponent fragmentComponent) {
    }
}
