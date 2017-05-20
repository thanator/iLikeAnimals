package ru.sberbank.mobile.common.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import ru.sberbank.backgroundtaskssample.R;

/**
 * @author QuickNick
 */
public class AnimalsActivity extends AppCompatActivity {

    private AnimalsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mAdapter = new AnimalsAdapter();
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);
    }
}
