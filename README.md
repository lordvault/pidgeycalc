<h1>PIDGEYCALC</h1>

Pidgey calc is an application created to apply some adquired knowledge on Java and Angular technologies. 

![Heroku](https://heroku-badge.herokuapp.com/?app=pidgey-calc)

At this moment the application have includede the following technologies: 
 - Spring boot
 - Gradle
 
As CI/CD is included the use of: 
 - Heroku 

Funtions:

**List**
- localhost:8081/api/list

**Calculate**

- localhost:8081/api/calculate:
Inside body: 
```{
  "listToCalculate": [
    {
      "referencePokemon": {
        "number": 10,
        "name": "Caterpie",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 178,
      "pokemonQuantity": 15
    },
    {
      "referencePokemon": {
        "number": 13,
        "name": "Weedle",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 317,
      "pokemonQuantity": 28
    },
    {
      "referencePokemon": {
        "number": 16,
        "name": "Pidgey",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 35,
      "pokemonQuantity": 3
    },
    {
      "referencePokemon": {
        "number": 265,
        "name": "Wurmple",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 155,
      "pokemonQuantity": 13
    },
    {
      "referencePokemon": {
        "number": 293,
        "name": "Whismur",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 84,
      "pokemonQuantity": 3
    },
    {
      "referencePokemon": {
        "number": 519,
        "name": "Pidove",
        "requiredCandiesToEvolve": 12
      },
      "candiesQuantity": 91,
      "pokemonQuantity": 8
    }
  ]
}```
