FROM openjdk:8
EXPOSE 9922
ARG JAR_FILE=target/api-transaction.jar
COPY ${JAR_FILE} api-transaction.jar
ENTRYPOINT ["java","-jar","/api-transaction.jar"]