package com.lordvault.pokemon.pidgeycalc.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordvault.pokemon.pidgeycalc.entities.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class DataRetrieverGateway {

    @Value("classpath:static/pokemons.json")
    private Resource resourceFile;

    public List<Pokemon> retrieveStoredPokemons(){
        ObjectMapper mapper  = new ObjectMapper();
        TypeReference<List<Pokemon>> typeReference = new TypeReference<List<Pokemon>>(){};
        try {
            InputStream inputStream =  resourceFile.getInputStream();
            List<Pokemon> pokemons = mapper.readValue(inputStream,typeReference);
            System.out.println("Pokemon retrieved "+pokemons);
            return pokemons;
        } catch (IOException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean pokemonExist(Pokemon toReview){
        return retrieveStoredPokemons().contains(toReview);
    }
}
