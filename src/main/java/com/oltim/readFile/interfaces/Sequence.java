package com.oltim.readFile.interfaces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Sequence {

    static Set<Integer> getIncreasingSequencesSizes(List<Integer> integerList) {
        boolean newSeq = true;
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 1; i < integerList.size(); i++) {
            if (integerList.get(i) <= integerList.get(i - 1)) {
                newSeq = true;
                if (count != 0) {
                    set.add(count);
                    count = 0;
                }
            } else if (integerList.get(i) > integerList.get(i - 1) && newSeq) {
                newSeq = false;
                count += 2;
            } else if (integerList.get(i) > integerList.get(i - 1) && !newSeq) {
                count++;
            }
        }
        return set;
    }

    static Set<Integer> getDescendingSequencesSizes(List<Integer> integerList) {
        boolean newSeq = true;
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 1; i < integerList.size(); i++) {
            if (integerList.get(i) >= integerList.get(i - 1)) {
                newSeq = true;
                if (count != 0) {
                    set.add(count);
                    count = 0;
                }
            } else if (integerList.get(i) < integerList.get(i - 1) && newSeq) {
                newSeq = false;
                count += 2;
            } else if (integerList.get(i) < integerList.get(i - 1) && !newSeq) {
                count++;
            }
        }
        return set;
    }
}
