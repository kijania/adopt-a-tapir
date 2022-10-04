# adopt-a-tapir

### Run the start script

1. Run: `./start.sh`

* it would compile application
* build application image
* run postgresql in your local docker
* run application

2. Create schema manually (until there is no automated data migration) in database

* `psql -h localhost -p 25432 -U postgres -d postgres`

* with password: `mysecretpassword`

* and run `CREATE TABLE tapir_animal_row (id UUID NOT NULL, name VARCHAR(255), registered DATE, size VARCHAR(255), adoption_status VARCHAR(255), PRIMARY KEY(id) );`

### Documentation:

When application is running documentation in Swagger is available on:

`http://localhost:8080/docs/`

### Stop postgresql container:

Run: `./stop.sh`