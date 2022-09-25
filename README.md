# adopt-a-tapir

## animal-shelter-api

REST api. 

Could be run from `adopt-a-tapir` directory by : `sbt animal-shelter-api/run` and tested with the example curl:

`curl --location --request POST 'localhost:8080/animal-shelter/animal' \
--header 'Content-Type: text/plain' \
--data-raw '{
"name" : "Jazzy",
"animalSize": "small",
"adoptionStatus": "in-quarantine"
}'`