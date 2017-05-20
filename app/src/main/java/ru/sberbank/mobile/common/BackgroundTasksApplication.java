package ru.sberbank.mobile.common;

import android.app.Application;

import ru.sberbank.mobile.common.animal.AnimalsStorage;
import ru.sberbank.mobile.common.animal.AnimalsStorageProvider;

/**
 * @author QuickNick.
 */

public class BackgroundTasksApplication extends Application implements AnimalsStorageProvider {

    private AnimalsStorage mAnimalsStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        mAnimalsStorage = new AnimalsStorage();
    }

    @Override
    public AnimalsStorage getAnimalsStorage() {
        return mAnimalsStorage;
    }
}
