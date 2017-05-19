package ru.sberbank.mobile.common.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.sberbank.backgroundtaskssample.R;

/**
 * @author QuickNick
 */
public class AnimalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals_activity);
    }
}
