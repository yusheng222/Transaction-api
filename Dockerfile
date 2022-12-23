FROM openjdk:8
EXPOSE 9922
ADD target/transaction-api.jar transaction-api.jar
ENTRYPOINT ["java","-jar","/transaction-api.jar"]