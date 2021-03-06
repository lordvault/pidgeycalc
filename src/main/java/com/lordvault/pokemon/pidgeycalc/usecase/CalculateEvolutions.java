package com.lordvault.pokemon.pidgeycalc.usecase;

import com.lordvault.pokemon.pidgeycalc.entities.CalcEvolutionsRequest;
import com.lordvault.pokemon.pidgeycalc.entities.CalcEvolutionsResponse;
import com.lordvault.pokemon.pidgeycalc.entities.Pokemon;
import com.lordvault.pokemon.pidgeycalc.entities.PokemonCalculation;
import com.lordvault.pokemon.pidgeycalc.gateway.DataRetrieverGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CalculateEvolutions {

    @Autowired
    private DataRetrieverGateway dataRetrieverGateway;

    private boolean TRANSFER_AFTER_EVOLVE = true;
    private int EVOLVE_EXPERIENCE = 500;
    private final long TIME_SPENT_FOR_EVOLUTION_SECONDS = 25;
    private long EGG_TIME_IN_SECONDS = TimeUnit.MINUTES.toSeconds(30);

    public CalcEvolutionsResponse doTheMaths(CalcEvolutionsRequest request){
        long spendTime = 0;
        int totalEvolutions = 0;
        int totalTransfersTodoFirst = 0;
        CalcEvolutionsResponse.CalcEvolutionsResponseBuilder calcEvolutionsResponseBuilder = CalcEvolutionsResponse.builder();
        List<PokemonCalculation> calculations = request.getListToCalculate();
        for(PokemonCalculation calculate :  request.getListToCalculate()){
            int candies = calculate.getCandiesQuantity();
            int quantity = calculate.getPokemonQuantity();
            if(TRANSFER_AFTER_EVOLVE && quantity > 1){
                candies += (quantity - 1);
            }

            Pokemon pokemon = calculate.getReferencePokemon();
            if(dataRetrieverGateway.pokemonExist(pokemon)){
                calculate.setQuantityToEvolve(candies / pokemon.getRequiredCandiesToEvolve());
                if(calculate.getQuantityToEvolve() > quantity ){
                    calculate.setQuantityToEvolve(quantity);
                }
                if(quantity > calculate.getQuantityToEvolve()){
                    calculate.setQuantityTransferFirst(quantity - calculate.getQuantityToEvolve());
                }
                System.out.println("Pokemon: "+pokemon.getName()+" Transfer first: "+calculate.getQuantityTransferFirst()+" pokemons to evolve:"+calculate.getQuantityToEvolve());

                spendTime+=(calculate.getQuantityToEvolve()*TIME_SPENT_FOR_EVOLUTION_SECONDS);
                totalEvolutions += calculate.getQuantityToEvolve();
                totalTransfersTodoFirst +=calculate.getQuantityTransferFirst();
            }else {
                calculate.setErrorMessage("Pokemon not found {"+pokemon.getName()+"}");
            }
        }

        String message = "Time for each evolution: %d sec, total time: %d/%d with possibility for evolve a total of %f";
        double expectedEvolutionsQuantity = EGG_TIME_IN_SECONDS/TIME_SPENT_FOR_EVOLUTION_SECONDS;
        System.out.println(TIME_SPENT_FOR_EVOLUTION_SECONDS+ " " + spendTime+ " " +  EGG_TIME_IN_SECONDS+ " " +  expectedEvolutionsQuantity);

        return calcEvolutionsResponseBuilder.spendTime(spendTime).totalEvolutions(totalEvolutions).totalEggsToUse((int) (spendTime / EGG_TIME_IN_SECONDS))
                .totalExperience(totalEvolutions*EVOLVE_EXPERIENCE).listToCalculate(calculations).totalTransfersTodoFirst(totalTransfersTodoFirst)
                .notes(String.format(message, TIME_SPENT_FOR_EVOLUTION_SECONDS, spendTime, EGG_TIME_IN_SECONDS, expectedEvolutionsQuantity))
                .build();

    }
}
