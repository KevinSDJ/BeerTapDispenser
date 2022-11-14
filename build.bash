#!/bin/bash
echo "Run postgres container"
echo "-------------------------------------------------- >"
docker run -d -p 5432:5432 -it --rm -e POSTGRES_USER=test -e POSTGRES_PASSWORD=testpassw -e POSTGRES_DB=appdb postgres:15.1-alpine;
echo "Run app"
echo "-------------------------------------------------- >"
./mvnw spring-boot:run