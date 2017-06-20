package ru.sberbank.mobile.common.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import ru.sberbank.mobile.common.animal.db.AnimalsDao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Tan-DS on 6/19/2017.
 */
public class AnimalsStorageTest {

    public AnimalsStorage mAnimalsStorage;

    @Mock
    public  AnimalsDao mAnimalsDaoMock;

    @Mock
    public AnimalsStorage.OnContentChangeListener mChangeListner;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        mAnimalsStorage = new AnimalsStorage(mAnimalsDaoMock);
        mAnimalsStorage.addOnContentChangeListener(mChangeListner);
    }

    @After
    public void tearDown() throws Exception {
        mAnimalsStorage.removeOnContentChangeListener(mChangeListner);
    }

    @Test
    public void getAnimalsCount() throws Exception {
        List<Animal> animals = EntitiesGenerator.createRandomAnimalsList(true);
        Mockito.when(mAnimalsDaoMock.getAnimals()).thenReturn(animals);
        int getCountFromStorage = mAnimalsStorage.getAnimalsCount();
        Mockito.verify(mAnimalsDaoMock, Mockito.times(1)).getAnimals();
        assertThat(animals.size(), is(getCountFromStorage));
    }

    @Test
    public void getAnimals() throws Exception {
        List<Animal> animals = EntitiesGenerator.createRandomAnimalsList(true);
        Mockito.when(mAnimalsDaoMock.getAnimals()).thenReturn(animals);
        List <Animal> animalsReal = mAnimalsStorage.getAnimals();
        Mockito.verify(mAnimalsDaoMock, Mockito.times(1)).getAnimals();
        assertThat(animalsReal, is(animals));
    }

    @Test
    public void deleteAnimal() throws Exception {
        Animal animal = EntitiesGenerator.createRandomAnimal(true);
        Mockito.when(mAnimalsDaoMock.getAnimalById(1)).thenReturn(animal);
        mAnimalsStorage.deleteAnimal(1);
        Mockito.verify(mAnimalsDaoMock, Mockito.times(1)).deleteAnimal(animal);
        Mockito.verify(mChangeListner, Mockito.times(1)).onAnimalAdded(animal);
    }

    @Test
    public void updateAnimal() throws Exception {
        Animal oldAnimal = EntitiesGenerator.createRandomAnimal(true);
        Animal newAnimal = EntitiesGenerator.createRandomAnimal(true);
        Mockito.when(mAnimalsDaoMock.getAnimalById(1)).thenReturn(oldAnimal);
        mAnimalsStorage.updateAnimal(1, newAnimal);
        Mockito.verify(mAnimalsDaoMock, Mockito.times(1)).updateAnimal(oldAnimal, newAnimal);
        Mockito.verify(mChangeListner, Mockito.times(1)).onAnimalAdded(oldAnimal);
    }

   @Test
    public void addAnimal() throws Exception {
        Animal animal = EntitiesGenerator.createRandomAnimal(true);
        mAnimalsStorage.addAnimal(animal);
        Mockito.verify(mAnimalsDaoMock).insertAnimal(animal);
        Mockito.verify(mChangeListner, Mockito.times(1)).onAnimalAdded(animal);
    }

}