/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/7/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NationalityDiscoveryService {

    private static final Map<String, String> nationalityMappings;

    static {
        nationalityMappings = new HashMap<>();
        nationalityMappings.put("american", "United States of America");
        nationalityMappings.put("german", "Germany");
        nationalityMappings.put("french", "France");
        nationalityMappings.put("british", "United Kingdom");
        nationalityMappings.put("italian", "Italy");
    }

    public static String getCountry(String nationality) {
        return nationalityMappings.get(nationality.toLowerCase());
    }
}
