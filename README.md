# adopt-a-tapir

## animal-shelter-api

### REST api module

build new image: 

`sbt docker`

image can be run locally via: 

`java -jar animal-shelter-api/target/scala-2.13/animal-shelter-api.jar`

application can be tested with HTTP request:

`curl --location --request POST 'localhost:8080/animal-shelter/animal' \
--header 'Content-Type: text/plain' \
--data-raw '{
"name" : "Jazzy",
"animalSize": "small",
"adoptionStatus": "in-quarantine"
}'`

documentation in Swagger is available on:

`http://localhost:8080/docs/`