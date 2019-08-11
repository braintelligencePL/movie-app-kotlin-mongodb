# Movie application

#### [![Build Status](https://travis-ci.org/braintelligencePL/movie-recruitment-task.svg?branch=master)](https://travis-ci.org/braintelligencePL/movie-recruitment-task) 

# Prerequisites
1. You need an API key. Provide it as an environment variable.

```bash
export API_KEY="your_api_key"
```

2. Optionally when running application from IntelliJ Idea:  
```bash
Go to: /run/edit_configurations/your_configuration/enviroment_variables

API_KEY=your_api_key
```

<br> 

# Endpoints:
To make easier for mobile developer there is only one endpoint that contains most of the information needed. Payload is not big so I guess that's good idea.

`GET: /movies?title="Fast and the Furious"` 
```json
{
    "id": "tt0232500",
    "name": "The Fast and the Furious",
    "movieTime": "106 min",
    "description": "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
    "releaseDate": "22 Jun 2001",
    "externalRatings": [
        {
            "source": "Internet Movie Database",
            "value": "6.8/10"
        },
        {
            "source": "Rotten Tomatoes",
            "value": "53%"
        },
        {
            "source": "Metacritic",
            "value": "58/100"
        },
        {
            "source": "Imdb",
            "value": "6.8"
        }
    ],
    "internalReviews": [] 
}
```

<br>

## REST API documentation
```bash
// online - heroku instance
https://movie-recruitment-task.herokuapp.com/api/swagger-ui.html

// localhost
http://localhost:8080/api/swagger-ui.html
```

## Build docker image
```bash
./gradlew jibDockerBuild
```

# Things done:
- merged movie-api response imdbRating and Ratings as one list of ratings
- there is internalRating=(our customer rating and review) and externalRating=(returned from movie-api - only rating)
- CI - travis.
- deployed to Heroku - instance sleeps after 30min. Give a moment for instance to start. 
- mongodb is sitting on cloud.mongodb.com.
- simple pipeline Heroku waits for CI to pass before deploy new version.

# Things done (but not clear): 
- I assumed that second point from Challenge `movie times` is meant for `movie time` meaning `Runtime` of one movie (not all Fast & Furious movies - seems pointless just to return all times of movies).
- As Third point is similar to Second one I merged that endpoint into one. Search movie by title. 

# Things that might be done: 
- API versioning - because that's a first iteration
- All endpoints are internal for public ones create an annotation @PublicEndpoint 
- Standardize the format of returned movie-api ratings e.g. 5/10
- Different Mongodb instances. This application has same DB for local/prod cloud.mongodb instance.  
