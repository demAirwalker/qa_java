package com.example;

import java.util.List;

public class Lion {

    private final boolean hasMane;
    private final Predator predator;

    public Lion(String sex, Predator predator) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }

        this.predator = predator;
    }

    public int getKittens() throws Exception {
        if (predator instanceof Feline) {
            return ((Feline) predator).getKittens();
        }
        return 1;
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}
