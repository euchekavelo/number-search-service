FROM openjdk:17
RUN mkdir /app
COPY ./build/libs/*.jar /app/number-search-service.jar
EXPOSE 8080
WORKDIR /app
CMD java -jar number-search-service.jar