package com.example.Precept.Backend.APIs.Settings.PeopleManager.StaffResourceManager;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class SttaffEmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return true;
    }
}
