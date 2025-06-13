FROM debian:bookworm-slim

# Variables opcionales
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

# Solo instalamos dependencias mínimas
RUN apt-get update && \
    apt-get install -y --no-install-recommends libstdc++6 && \
    rm -rf /var/lib/apt/lists/*

# Copiamos el binario
WORKDIR /app
COPY target/graalvm-service /app/graalvm-service

RUN chmod +x /app/graalvm-service

CMD ["/app/graalvm-service"]