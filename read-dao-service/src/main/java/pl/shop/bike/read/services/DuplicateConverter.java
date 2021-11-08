package pl.shop.bike.read.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DuplicateConverter {

    public <T> List<T> removeDuplicatesFromList(List<T> listToConvert){
        Set<T> set = new LinkedHashSet<>();
        set.addAll(listToConvert);

        List<T> tmpList = new ArrayList<>();
        tmpList.addAll(set);

        return tmpList;
    }
}
