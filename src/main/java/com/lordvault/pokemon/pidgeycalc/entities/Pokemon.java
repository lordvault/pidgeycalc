package com.lordvault.pokemon.pidgeycalc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    int number;
    String name;
    int requiredCandiesToEvolve;
}
