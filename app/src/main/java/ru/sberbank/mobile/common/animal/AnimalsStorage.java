package ru.sberbank.mobile.common.animal;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.sberbank.mobile.common.animal.db.AnimalsDao;

/**
 * @author QuickNick
 */
public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();

    private final AnimalsDao mAnimalsDao;
    private final List<OnContentChangeListener> mOnContentChangeListeners;

    public AnimalsStorage(AnimalsDao animalsDao) {
        mAnimalsDao = animalsDao;
        mOnContentChangeListeners = new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        return mAnimalsDao.getAnimals();
    }

    public void addAnimal(Animal animal) {
        mAnimalsDao.insertAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }

    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }

    public interface OnContentChangeListener {

        void onAnimalAdded(AnimalsStorage sender, Animal animal);
    }
}
