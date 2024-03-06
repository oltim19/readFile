package com.oltim.readFile.interfaces;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.naturalOrder;

public interface ListCalculator {
    static Integer calculateMinOfList(List<Integer> integerList) {
        return integerList.stream().min(naturalOrder()).get();
    }

    static Integer calculateMaxOfList(List<Integer> integerList) {
        return integerList.stream().max(Comparator.naturalOrder()).get();
    }

    static Double calculateAverageOfList(List<Integer> integerList) {
        return integerList.stream().collect(Collectors.averagingInt(Integer::valueOf));
    }

    static Double calculateMedianOfList(List<Integer> integerList) {
        Double mid;
        List<Integer> list = new ArrayList<>(integerList);
        Collections.sort(list);
        int index = list.size() / 2;
        if (list.size() % 2 != 0) {
            mid = Double.valueOf(list.get(index));
        } else {
            mid = (Double.valueOf(list.get(index)) + (Double.valueOf(list.get(index - 1)))) / 2;
        }
        return mid;
    }

    static Integer calculateMaxOfList(Set<Integer> integerSet) {
        return integerSet.size() < 2 ? 0 : integerSet.stream().max(naturalOrder()).get();
    }
}
