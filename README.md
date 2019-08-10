# Movie application

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

# Endpoints:
To make easier for mobile developer there is only one endpoint that contains most of the information needed. Payload is not big so I guess that's good idea.

`GET: /movies?title="Fast and the Furious"` -   
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
    "internalReviews": [] // empty when user did not leave any review (review = review + rating)
}
```


## REST API documentation
```bash
http://localhost:8080/api/swagger-ui.html
```

# Things done:
- merged imdbRating with Ratings as just one List of Ratings
- there is internalRating=(our customer rating and review) and externalRating=(returned from movie-api - only rating)

# Things done (but not clear): 
- I assumed that second point from Challenge `movie times` is meant for `movie time` meaning `Runtime` of one movie (not all Fast & Furious movies - seems pointless just to return all times of movies).
- As Third point is similar to Second one I merged that endpoint into one. Search movie by title. 

# Things that might be done: 
- API versioning - because that's a first iteration
- All endpoints are internal for public ones create an annotation @PublicEndpoint 
- Standardize the format of returned movie-api ratings e.g. 5/10
