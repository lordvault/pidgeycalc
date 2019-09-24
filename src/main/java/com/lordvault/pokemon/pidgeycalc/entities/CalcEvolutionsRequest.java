package com.lordvault.pokemon.pidgeycalc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalcEvolutionsRequest {

    List<PokemonCalculation> listToCalculate;

}
