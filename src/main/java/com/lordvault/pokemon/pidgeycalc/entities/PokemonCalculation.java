package com.lordvault.pokemon.pidgeycalc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonCalculation {
    Pokemon referencePokemon;
    int candiesQuantity;
    int pokemonQuantity;
    int quantityTransferFirst = 0;
    int quantityToEvolve = 0;
    String errorMessage;
}
