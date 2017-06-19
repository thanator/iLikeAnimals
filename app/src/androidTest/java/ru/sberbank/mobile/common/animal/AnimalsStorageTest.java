package ru.sberbank.mobile.common.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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
    public  AnimalsDao animalsDaoMock;

    @Before
    public void setUp() throws Exception {
        mAnimalsStorage = new AnimalsStorage(animalsDaoMock);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAnimalsCount() throws Exception {

    }

    @Test
    public void getAnimals() throws Exception {
        List<Animal> animals = EntitiesGenerator.createRandomAnimalsList(true);
        Mockito.when(animalsDaoMock.getAnimals())
                .thenReturn(animals);
        List <Animal> animalsReal = mAnimalsStorage.getAnimals();
        Mockito.verify(animalsDaoMock, Mockito.times(1)).getAnimals();
        assertThat(animalsReal, is(animals));
    }

    @Test
    public void deleteAnimal() throws Exception {
    }

    @Test
    public void updateAnimal() throws Exception {
    }

    @Test
    public void addAnimal() throws Exception {
    }

}