package com.lordvault.pokemon.pidgeycalc.controller;

import com.lordvault.pokemon.pidgeycalc.entities.CalcEvolutionsRequest;
import com.lordvault.pokemon.pidgeycalc.entities.CalcEvolutionsResponse;
import com.lordvault.pokemon.pidgeycalc.entities.Pokemon;
import com.lordvault.pokemon.pidgeycalc.entities.PokemonCalculation;
import com.lordvault.pokemon.pidgeycalc.gateway.DataRetrieverGateway;
import com.lordvault.pokemon.pidgeycalc.usecase.CalculateEvolutions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8081")
public class PidgeyService {

    @Autowired
    private DataRetrieverGateway dataRetrieverGateway;

    @Autowired
    private CalculateEvolutions calculateEvolutions;

    @RequestMapping("/")
    public String index(){
        StringBuffer servicesBuffer = new StringBuffer();
        servicesBuffer.append(" <br/><a href=\"/list\">Pokemon List</a>");
        return "Welcome to Pidgeycalc Service "+servicesBuffer.toString();
    }

    @GetMapping("/list")
    public List<Pokemon> retrieveAllPokemonList() {
        return dataRetrieverGateway.retrieveStoredPokemons();
    }

    @GetMapping("/test")
    public CalcEvolutionsRequest test() {
        Pokemon pidgey = Pokemon.builder().name("pidgey").number(13).requiredCandiesToEvolve(12).build();
        PokemonCalculation pokemonCalculation = PokemonCalculation.builder().candiesQuantity(1).pokemonQuantity(15).referencePokemon(pidgey).build();
        CalcEvolutionsRequest calcEvolutionsRequest = CalcEvolutionsRequest.builder().listToCalculate(Arrays.asList(pokemonCalculation)).build();
        return calcEvolutionsRequest;
    }

    @PostMapping("/calculate")
    public CalcEvolutionsResponse calculatePokemonEvolutions(@RequestBody CalcEvolutionsRequest request) {
        return calculateEvolutions.doTheMaths(request);
    }

}
