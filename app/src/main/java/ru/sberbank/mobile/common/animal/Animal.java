package ru.sberbank.mobile.common.animal;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * @author QuickNick
 */
public class Animal implements Serializable {

    private long mId;
    private String mSpecies;
    private int mAge;
    private String mName;

    public Animal() {

    }

    public Animal(String species, int age, String name) {
        mSpecies = species;
        mAge = age;
        mName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Animal)) {
            return false;
        }
        Animal animal = (Animal) o;
        return mId == animal.mId &&
                mAge == animal.mAge &&
                Objects.equal(mSpecies, animal.mSpecies) &&
                Objects.equal(mName, animal.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mSpecies, mAge, mName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mId", mId)
                .add("mSpecies", mSpecies)
                .add("mAge", mAge)
                .add("mName", mName)
                .toString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public void setSpecies(String species) {
        mSpecies = species;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
