FROM openjdk:17
LABEL authors="aboubacar"
ARG target=target/todo.jar
EXPOSE 8088
ADD ${target} todo.jar
ENTRYPOINT ["java", "-jar", "/todo.jar"]