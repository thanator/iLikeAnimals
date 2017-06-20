package ru.sberbank.mobile.common.animal;

import java.util.ArrayList;
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
            listener.onAnimalAdded(animal);
        }
    }


    public void updateAnimal(long id, Animal newAnimal){
        Animal animal = mAnimalsDao.getAnimalById(id);
        mAnimalsDao.updateAnimal(animal, newAnimal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(animal);
        }
    }


    public void addAnimal(Animal animal) {
        mAnimalsDao.insertAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(animal);
        }
    }

    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }

    public interface OnContentChangeListener {

        void onAnimalAdded(Animal animal);
    }
}
