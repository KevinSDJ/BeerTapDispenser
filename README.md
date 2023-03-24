# Notes

#### command to run test by profile :
- Production:  mvn spring-boot:run -Dspring-boot.run.profiles=prod
- Development: mvn spring-boot:run -Dspring-boot.run.profiles=dev
- Testing: mvn spring-boot:run -Dspring-boot.run.profiles=test

#### If you want to test the api from your computer without installing maven or java, you can use docker compose, yes at least have a docker installer, docker-compose
- docker-compose up , to run the application with its dependencies
- docker-compose down to run the application with its dependencies
