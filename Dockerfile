FROM openjdk:8
ADD build/docker/movie-recruitment-task-0.1.0.jar movie-recruitment-task.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","movie-recruitment-taskr.jar"]
