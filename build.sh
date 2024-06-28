#!/bin/bash

## Build the catalog Docker image
#cd "/d/Spring Project/kimhab-microservice-2-nacos/catalog-service" || exit
#docker build -t ms-2-nacos/catalog-service:1.0-SNAPSHOT .
#
## Build the gateway Docker image
#cd "/d/Spring Project/kimhab-microservice-2-nacos/gateway-service" || exit
#docker build -t ms-2-nacos/gateway-service:1.0-SNAPSHOT .

#cd "/d/Spring Project/kimhab-microservice-2-nacos/catalog-service" || exit
#docker build -t ms-2-nacos/catalog-service:1.0-SNAPSHOT .





# Define an array of directories and image names
declare -A services=(
    ["/d/Spring Project/kimhab-microservice-2-nacos/catalog-service"]="catalog-service"
    ["/d/Spring Project/kimhab-microservice-2-nacos/gateway-service"]="gateway-service"
    # Add more directories and image names as needed
)

# Loop through the array and build each Docker image
for dir in "${!services[@]}"; do
    image_name=${services[$dir]}
    cd "$dir" || { echo "Failed to navigate to $dir"; exit 1; }
    docker build -t "$image_name" .
done

# Navigate to the directory containing docker-compose.yml
cd "/d/Spring Project/kimhab-microservice-2-nacos" || { echo "Failed to navigate to docker-compose directory"; exit 1; }

# Run Docker Compose
docker-compose up -d