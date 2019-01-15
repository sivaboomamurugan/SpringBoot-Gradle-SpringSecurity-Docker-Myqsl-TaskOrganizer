FROM java:8
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
RUN chmod 777 /app/start.sh
EXPOSE 8080
ENTRYPOINT ["/app/start.sh"]