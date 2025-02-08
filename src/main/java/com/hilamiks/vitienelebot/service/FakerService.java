package com.hilamiks.vitienelebot.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FakerService {

    Faker faker = new Faker(Locale.of("ru-RU"));

    public String getFakeName() {
        return faker.name().fullName();
    }
}
