# Commerce Hub

This project aims to provide a way to browse sales of multiple e-commerce shops.
With that said, it has an ETL service that extracts data from 
multiple e-commerce shops / platforms on a regular basis.

There are 5 services in total:
- Frontend (React JS)
    - commercehub-web-ui `This is shelf under another repository`


- Backend (Java)
    - ch-linker
        - One has to authorize the app before data extraction takes place, this service aims to:
            - Handles linking related tasks, such as link or unlink a shop
            - Provides relevant information required by frontend
    - ch-resource-server
        - Provides sales related information
    - ch-etl
        - Extracts data from authorized shops on a regular basis
    - ch-db-setup (JVM Required)
        - Handles RDBMS schema migration


# Frameworks & Stacks

- Frontend (React JS)
    - React MUI
    - React Router
    - Auth0


- Backend (Java)
    - Quarkus, the Supersonic Subatomic Java Framework
    - Mutiny & RxJava
    - Flyway
    - GCP
        - Firestore
        - Big Query
        - Cloud Task
        - Cloud Scheduler

# Get Started
- Fill up settings (mostly auth related) in application.properties for below services:
    - ch-linker
    - ch-resource-server


- Create a cloud storage bucket with the following name:
    - commercehub-etl (This is meant for BigQuery & Cloud Task)


- Set the following environment variables:
    - GOOGLE_APPLICATION_CREDENTIALS<br/>
      (Credential must have read & write access to Cloud Tasks, Cloud Storage, Firestore & Big Query)

Once done, you may start services using command below:
- ``` mvn clean install ```
- ``` mvn quarkus:dev -pl ch-linker ```
- ``` mvn quarkus:dev -pl ch-resource-server ```
- ``` mvn quarkus:dev -pl ch-etl ```

# Database Schema Migration
1. Run service `ch-db-setup` using the following commands:
- ``` mvn clean install ```
- ``` mvn quarkus:dev -pl ch-db-setup ```

2. Open swagger UI:
- URL: http://localhost:8084/db/q/swagger-ui/

3. Trigger API:
- If schema does not exist, use `/db/database/create`
- Otherwise, use `/db/database/update`

> Please note that `ch-db-setup` does NOT work in native but JVM

# TODO
TODO

# Closing Note
TODO
