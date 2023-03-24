FROM openjdk:19-alpine3.16
COPY ./target/beer-dispenser-1.0.0.jar  ./app.jar
ENTRYPOINT [ "java","-jar","./app.jar" ]
