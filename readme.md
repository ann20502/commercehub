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

# Workflow

![Application Workflow](https://user-images.githubusercontent.com/6287166/173289489-52db7042-9d76-40dc-af85-2d4ea9abe38c.png)
*** Highlighted in red are the 5 services

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
- Create a cloud storage bucket and fill the name in `ch-etl` `application.properties`


- Create a cloud task queue with the following name:
    - etl-queue


- Fill up settings (mostly auth related) in `application.properties` of below services:
    - ch-linker
    - ch-resource-server


- Set the following environment variables:
    - GOOGLE_APPLICATION_CREDENTIALS<br/>
      (Credential must have read & write access to Cloud Tasks, Cloud Storage, Firestore & Big Query)


- Create Firestore indexes for the following services:
    - ch-linker
    - ch-etl

> Index definitions can be found in respective modules.<br/>
> Definition files can be used with firebase CLI ``` firebase deploy --only firestore:indexes ```

Once done, you may start services using command below:
- ``` mvn clean install ```
- ``` mvn quarkus:dev -pl ch-linker ```
- ``` mvn quarkus:dev -pl ch-resource-server ```
- ``` mvn quarkus:dev -pl ch-etl ```

# Deployment
If you are using GCP cloud run to host `ch-etl` module, you may configure properties below if authentication required:
  - quarkus.google.cloud.service-account-email

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
1. Refactor com.commercehub.link package (under ch-linker module) with Clean Architecture

# Closing Note
TODO
