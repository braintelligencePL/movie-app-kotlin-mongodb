# Movie application

# Prerequisites
1. You need an API key. Provide it as an environment variable.

```bash
export API_KEY=your_api_key
```

2. Optionally when running application from IntelliJ Idea:  
```bash
Go to: /run/edit_configurations/your_configuration/enviroment_variables

API_KEY="your_api_key"
```

# Endpoints:
 

## REST API documentation
```bash
http://localhost:8080/api/swagger-ui.html
```

# Things done (but not clear): 

- I assumed that second point from Challenge `movie times` is meant for `movie time` meaning `Runtime` of one movie (not all Fast & Furious movies - seems pointless just to return all times of movies).

- As Third point is similar to Second one I merged that endpoint into one. Search movie by title. 

# Things done:
- merged imdbRating with Ratings as just one List of Ratings

# Things that might be done: 
- API versioning - because that's a first iteration
- All endpoints are internal for public ones create an annotation @PublicEndpoint 
- Standardize the format of returned movie-api ratings e.g. 5/10 (but that might lead to miss information)
