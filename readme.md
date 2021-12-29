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
TODO -- HERE

# TODO
TODO

# Closing Note
TODO
