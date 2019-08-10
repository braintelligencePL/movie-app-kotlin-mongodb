FROM openjdk:8
ADD build/docker/news-service-0.1.0.jar docker-news-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","docker-news-service.jar"]