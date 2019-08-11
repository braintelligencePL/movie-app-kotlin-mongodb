# Movie application

#### [![Build Status](https://travis-ci.org/braintelligencePL/movie-recruitment-task.svg?branch=master)](https://travis-ci.org/braintelligencePL/movie-recruitment-task) 

# Prerequisites
1. You need an API key. Provide it as an environment variable. http://www.omdbapi.com/apikey.aspx

```bash
export API_KEY="your_api_key"
```

2. Optionally when running application from IntelliJ Idea: <br>
Go to: `/run/edit_configurations/your_configuration/enviroment_variables`  
```bash
API_KEY=your_api_key
```

3. Also you need credentials for your mongodb instance: 
```bash
export MONGO_HOST="your_host"
export MONGO_USERNAME="your_username"
export MONGO_PASSWORD="your_password"
```

<br> 

# Endpoints:
To make easier for mobile developer there is only one endpoint that contains most of the information needed. Payload is not big so I guess that's good idea.

Online: https://movie-recruitment-task.herokuapp.com/movies?title=The%20Fast%20and%20the%20Furious

Endpoint: `GET: /movies?title="Fast and the Furious"` 
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

Online: https://movie-recruitment-task.herokuapp.com/api/swagger-ui.html

Localhost: http://localhost:8080/api/swagger-ui.html  

<br>

## Build docker image
```bash
./gradlew jibDockerBuild
```

<br>

todo(rating can be from 1 to 5)

# Things done:

### Application
- merged movie-api response imdbRating and Ratings as one list of ratings
- there is internalRating=(our customer rating and review) and externalRating=(returned from movie-api - only rating)
- two DBS on cluster: prod and test (prod for Heroku, test for local development and tests)

### Infrastructure
- mongodb cluster is from cloud.mongodb.com (Replica set - 3 nodes)
- CI - travis - there is problem with embedded mongo on travis, so app connects to cloud.mongodb.com - tests should be self-contained and I'm fan of in-memory for integration tests. Generally speaking depends on needs.)
- deployed to Heroku - instance sleeps after 30min. Give a moment for instance to start. 
- simple pipeline Heroku waits for CI to pass before deploy new version.

# Things done (but not clear): 
- I assumed that second point from Challenge `movie times` is meant for `movie time` meaning `Runtime` of one movie (not all Fast & Furious movies - seems pointless just to return all times of movies).
- As Third point is similar to Second one I merged that endpoint into one. Search movie by title. 

# Things that might be done: 
- API versioning - because that's a first iteration
- All endpoints are internal for public ones create an annotation @PublicEndpoint 
- Standardize the format of returned movie-api ratings e.g. 5/10
- Different Mongodb for each environment. This application has same DB for local/prod cloud.mongodb instance.  
- Aggregating average internal rating offline. Right now it is performed when request is coming which is bad because when more reviews will come than response time will be longer.
