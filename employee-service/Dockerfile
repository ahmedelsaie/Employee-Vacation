# Multi-stage Optimized Dockerfile
# the first stage of our build will build and extract the layers
FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /employee
COPY ./pom.xml ./
## store maven dependencies so next build doesn't have to download them again
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn clean package -DskipTests -Djar.finalName=employee-service.jar

# the second stage of our build will copy the extracted layers
FROM openjdk:17.0.1-jdk-slim as runtime
WORKDIR /employee
COPY --from=builder /employee/target/employee-service.jar .
ADD entrypoint.sh .
ENTRYPOINT ["./entrypoint.sh"]


