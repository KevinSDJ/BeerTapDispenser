#!/bin/bash

CONTAINER_NAME=(`docker ps  --format "table {{.Names}}"`);

if [ ${CONTAINER_NAME[1]} ]; then
 echo "postgres container active ";
 echo -e "\n ----------- skipping postgres run -------------"
 ./mvnw spring-boot:run
else
  echo "postgres container inactive"
  echo -e "\n -------------- postgres run ----------------->"
  docker run -d -p 5432:5432 -it --rm -e POSTGRES_USER=test -e POSTGRES_PASSWORD=testpassw -e POSTGRES_DB=appdb postgres:15.1-alpine;
  echo "spring-boot run"
  echo -e "\n ------------------------------->"
  ./mvnw spring-boot:run
fi

unset CONTAINER_NAME