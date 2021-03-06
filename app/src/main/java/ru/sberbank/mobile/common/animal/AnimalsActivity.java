package ru.sberbank.mobile.common.animal;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import ru.sberbank.backgroundtaskssample.R;

/**
 * @author not QuickNick
 */
public class AnimalsActivity extends AppCompatActivity {

    private static final int ANIMALS_LOADER_ID = 1;

    private AnimalsStorage mAnimalsStorage;
    private AnimalsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentResolver resolver = getContentResolver();
        String androidID = Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID);

        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        setContentView(R.layout.animals_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mAdapter = new AnimalsAdapter();
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(ANIMALS_LOADER_ID, null, new AnimalsLoaderCallbacks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animals_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.add_animal_menu_item: {
                startActivity(DoSomeThingWithAnimalActivity.newIntent(this));
                break;
            }
            case R.id.chosing_one: {
                DialogFragment newFragment = new AnimalChoosingFragment();
                Bundle args = new Bundle();
                args.putInt("count", mAnimalsStorage.getAnimalsCount());
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "Choosing");
                break;
            }
            default: {
                handled = super.onOptionsItemSelected(item);
                break;
            }
        }
        return handled;
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
