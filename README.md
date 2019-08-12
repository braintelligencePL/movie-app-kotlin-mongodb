# Movie application

#### [![Build Status](https://travis-ci.org/braintelligencePL/movie-recruitment-task.svg?branch=master)](https://travis-ci.org/braintelligencePL/movie-recruitment-task)

## Bussiness case:  

User can search for movies that are in the cinema. 

User can search for any movie that is out there by title and get detailed response about the movie. 
 
Admin can use that to search for movies that could be in the cinema and add movie to the cinema repertoire (by imdbId).  

Admin can also change showTime and price of the movie. 

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

## REST API documentation

Online: https://movie-recruitment-task.herokuapp.com/api/swagger-ui.html

Localhost: http://localhost:8080/api/swagger-ui.html  

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
    "internalReviews": [
        {
            "rating": "3",
            "review": "good film",
            "date": "2019-08-11T12:19:31.379Z"
        },
        {
            "rating": "4",
            "review": "bad movie",
            "date": "2019-08-11T12:19:31.380Z"
        }
    ]
}
```

Endpoint: `POST: /catalogs` - create new catalog
```json
{
    "name": "catalog name"
}
```

Endpoint `PUT: /catalogs` - update or add movies to cinema repertoire 
```json
{
    "catalogName": "123",
    "movies": [
        {
            "imdbId": "tt0232500",
            "showTime": {
                "time": [
                    11,
                    54
                ],
                "date": [
                    2019,
                    11,
                    25
                ]
            },
            "price": "123"
        }
    ]
}
```

Endpoint: `GET: /catalogs` - get all catalogs

<br>

## Build docker image
```bash
./gradlew jibDockerBuild
```

<br>

# Things done:

### Application
- merged movie-api response imdbRating and Ratings as one list of ratings
- there is internalRating=(our customer rating and review) and externalRating=(returned from movie-api - only rating)
- two DBS on cluster: prod and test (prod for Heroku, test for local development and tests)

### Infrastructure
- mongodb cluster is from cloud.mongodb.com (Replica set - 3 nodes)
- CI - travis - there is problem with embedded mongo on travis. Travis and local integration tests are connected to cluster with DBS named test. Generally IT should be self-contained and im fan of in-memory tests, but that solution is good too (good thing is we are testing against real database), but also we depend on some external service which is not good in the same time). Another solution might be docker-compose. 
- deployed to Heroku - instance sleeps after 30min. Give a moment for instance to start. 
- simple pipeline - Travis (runs tests) -> Heroku (waits for CI to pass before deploy)
- Different MongoDB for each environment. test, prod (test is used as local).   

# Things done (but not clear): 
- I assumed that second point from Challenge `movie times` is meant for `movie time` meaning `Runtime` of one movie (not all Fast & Furious movies - seems pointless just to return all times of movies). So I merged second and third point into one. 

# Things that might be done: 
- Commits should be done through PR
- API versioning - because that's a first iteration
- All endpoints are internal for public ones create an annotation @PublicEndpoint 
- Standardize the format of returned movie-api ratings e.g. 5/10
- Aggregating average internal rating offline. Right now it is performed when request is coming which is bad because when more reviews will come than response time will be longer.
- Add paging for internalReviews (maybe introduce completely new endpoint for it, because extending already existing functionality to paging would make this one endpoint a bit too complex).
- As you can create multiple catalogs application can be sold to other client with other repertoire.
