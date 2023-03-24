FROM openjdk:19-alpine3.16
COPY ./target/Beerdispenser-0.0.1-SNAPSHOT  ./app.jar
ENTRYPOINT [ "java","-jar","./app.jar" ]