# Contract first async api

## Start

`docker-compose up -d`

Use the "confluent" profile for using the Kafka schema registry, it can be consulted via the control center on http://localhost:9021


## Demo

### The initial setup

1. Create `vehicle.refueled` topic via the control center (Create with default)
2. Add a new schema -> `VehicleRefueled-initial.avsc`
3. Adapt the compatibility settings and change them to `forward` and click save
4. Show the maven plugin for generating Java DTO of Avro schema
5. Walk through producer code
6. Explain application.yml config
7. Explain application-confluent.yml config
8. Start the producer
9. Send a message
10. Show the schema in the consumer
11. Show the consumer code
12. Show the configuration
13. Start the consumer and show the message in the logs

### The new incompatible schema

1. Show the incompatible change in the schema
2. Upload the incompatible schema as new version and see the validation failing in the registry

### The deprecated city and new location

1. Show the new schema and explain the unknown default instead of null and the java annotation (Adapted Avro generation templates).
2. Upload the schema to the registry
3. Build the producer with the new schema in the maven plugin
4. Show them the deprecation in Java
5. Restart the producer
6. State that nothing is changed in the producer
7. Send a message
8. Show consumer does not break and receives message without location
9. Update the consumer with the new schema
10. Show the deprecation notice of City in the consumer
11. Send a message and see the location and city

### The forgot to register new schema in the schema registry

1. Change the schema with the version without city
2. Comment out setting the city in the producer
3. Build and start the producer
4. Send a message
5. See the failure in the logs

### Fix the previous failure and send a message with only location and no city

1. Upload schema in schema registry
2. Send message again
3. Watch consumer logs and see that while no city is send in the message, the default value from the "previous" schema is used for "city" in the consumer
4. Show the message in Kafka to verify unknown is not set in the message

### Show build time validation of local schema

1. run the maven plugin goal test-compatibility (show the compatibility check)
2. Useful in builds to validate current local schema is compatible with the one in the registry