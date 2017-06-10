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
 * @author not QuickNick
 */
public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();

    private final AnimalsDao mAnimalsDao;
    private final List<OnContentChangeListener> mOnContentChangeListeners;

    public int getAnimalsCount(){
        return mAnimalsDao.getAnimals().size();
    }

    public AnimalsStorage(AnimalsDao animalsDao) {
        mAnimalsDao = animalsDao;
        mOnContentChangeListeners = new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        return mAnimalsDao.getAnimals();
    }

    public void deleteAnimal(long id){
        Animal animal = mAnimalsDao.getAnimalById(id);
        mAnimalsDao.deleteAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }


    public void updateAnimal(long id, Animal newAnimal){
        Animal animal = mAnimalsDao.getAnimalById(id);
        mAnimalsDao.updateAnimal(animal, newAnimal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
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
