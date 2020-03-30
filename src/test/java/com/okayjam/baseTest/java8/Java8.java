package com.okayjam.baseTest.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/01/03 16:42
 **/
public class Java8 {

    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApple(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterWeightApple(List<Apple> inventory, double weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApple(List<Apple> inventory, String color, double weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ( flag && color.equals(apple.getColor()) || !flag && apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ( p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApple1(List<Apple> inventory, ApplePredicate p) {

        List<Apple> greenApple = filterApple(inventory, new AppleColorPredicate());

        List<Apple> redApple = filterApple(inventory, new ApplePredicate(){
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });


        return redApple;
    }

    public static List<Apple> filterApple2(List<Apple> inventory, ApplePredicate p) {

        List<Apple> redApple = filterApple(inventory, apple -> "red".equals(apple.getColor()));

        inventory.sort(Comparator.comparing(Apple::getColor));
        inventory.removeIf(apple -> apple.getColor().equals("red"));

        return redApple;
    }




}


class Apple {
    String color;
    double weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}


 interface ApplePredicate {
    boolean test (Apple apple);
}

interface Predicate<T> {
    boolean test (T t);
}


class AppleWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}


class AppleColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}