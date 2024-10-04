# READ ME


## Docker Instructions Setup
Create a docker network
```
docker network create my-network
```

Start MongoDB service via command line
```
docker run --name mongo --network my-network -p 27017:27017 -d mongodb/mongodb-community-server:latest

docker run -d --network my-network --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo
```

Start Mongo Express service via command line
```
docker run --name mongoexpress --network my-network -e ME_CONFIG_MONGODB_SERVER=mongo -e ME_CONFIG_MONGODB_AUTH_USERNAME=mongoadmin -e ME_CONFIG_MONGODB_AUTH_PASSWORD=secret -p 8081:8081 mongo-express
```

Start Zipkin service
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

To list all active containers
```
docker ps -a
```