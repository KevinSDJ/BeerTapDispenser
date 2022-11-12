FROM  ghcr.io/graalvm/jdk:java17-22.2.0
COPY ./target/Beerdispenser-0.0.1-SNAPSHOT  ./app.jar
ENTRYPOINT [ "java","-jar","./app.jar" ]