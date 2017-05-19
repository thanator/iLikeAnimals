package ru.sberbank.mobile.common.animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author QuickNick
 */
public class AnimalsGenerator {

    private static final List<Animal> sAnimals;

    static {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Кошка", 4, "Матильда"));
        animals.add(new Animal("Собака", 5, "Жучка"));
        animals.add(new Animal("Коза", 2, "Дереза"));
        animals.add(new Animal("Корова", 7, "Бурёнка"));
        animals.add(new Animal("Кенгуру", 1, "Джек"));

        animals.add(new Animal("Тигр", 9, "Тимур"));
        animals.add(new Animal("Медведь", 12, "Михаил Потапыч"));
        animals.add(new Animal("Дельфин", 3, "Джерри"));
        animals.add(new Animal("Мышь", 1, "Лариска"));
        animals.add(new Animal("Волк", 11, "Полкан"));

        sAnimals = Collections.unmodifiableList(animals);
    }

    public static List<Animal> getAnimals() {
        return Collections.unmodifiableList(sAnimals);
    }
}
