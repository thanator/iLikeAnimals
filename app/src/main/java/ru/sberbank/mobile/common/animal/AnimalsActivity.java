package ru.sberbank.mobile.common.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

import ru.sberbank.backgroundtaskssample.R;

/**
 * @author QuickNick
 */
public class AnimalsActivity extends AppCompatActivity {

    private static final int ANIMALS_LOADER_ID = 1;

    private AnimalsStorage mAnimalsStorage;
    private AnimalsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        setContentView(R.layout.animals_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mAdapter = new AnimalsAdapter();
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(ANIMALS_LOADER_ID, null, new AnimalsLoaderCallbacks());
    }

    private class AnimalsLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Animal>> {

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalsLoader(AnimalsActivity.this, mAnimalsStorage);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> data) {
            mAdapter.setAnimals(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
        }
    }
}
