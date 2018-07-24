package com.example.spacex.di.component;

import com.example.spacex.di.scope.ActivityScope;
import com.example.spacex.ui.activity.MainActivity;
import com.example.spacex.di.module.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
}
