# Implementación de un pipeline CI/CD para Spring Boot usando GitHub Actions

## Repositorio del proyecto

La ruta del repositorio para este proyecto es: [https://github.com/JuanLopezAranzazu/cliente-service/](https://github.com/JuanLopezAranzazu/cliente-service/)

## Definir compose

Crear archivo `compose.yaml`

```yaml
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=curso_springboot'
      - 'POSTGRES_PASSWORD=a1b2c3d4'
      - 'POSTGRES_USER=devdb'
    ports:
      - '5432'
    networks:
      - red-backend-app

  cliente-service:
    image: 'juanlopezaranzazu388/cliente-service:latest'
    ports:
      - '8080:8080'
    depends_on:
      - postgres
    environment:
      DB_URL: jdbc:postgresql://postgres/curso_springboot
      DB_USERNAME: devdb
      DB_PASSWORD: a1b2c3d4
      JPA_DDL: create-drop
    profiles:
      - app
    networks:
      - red-backend-app

networks:
  red-backend-app:
    driver: bridge
```

## Definir Pipeline CI/CD

Crear archivo `cicd-pipeline.yml`
```yml
name: CI/CD Pipeline

on:
  push:
    branches:
      - main

  pull_request:
    types: [opened, synchronize, reopened]

jobs:
  test:
    runs-on: ubuntu-latest

    permissions:
      contents: read
      checks: write  # Permitir la creación de checks de GitHub

    steps:
      - name: Check out the repository
        uses: actions/checkout@v4
        with:
          fetch-depth: 0

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'adopt'

      - name: Cache Maven dependencies
        uses: actions/cache@v4
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-

      - name: Install dependencies
        run: mvn install -DskipTests

      - name: Run Tests with Testcontainers and Generate Coverage Report
        env:
          TESTCONTAINERS_RYUK_DISABLED: "true" # Opcional: deshabilita Ryuk si es necesario
          DOCKER_HOST: tcp://localhost:2375
        run: mvn test jacoco:report

      - name: Upload Test and Coverage Results
        if: always()  # Este paso se ejecuta incluso si las pruebas fallan
        uses: actions/upload-artifact@v4
        with:
          name: test-results
          path: |
            target/surefire-reports
            target/site/jacoco/jacoco.xml

      - name: Display Test Results
        uses: dorny/test-reporter@v1
        with:
          name: Test Results
          path: target/surefire-reports/*.xml
          reporter: java-junit

  build-and-publish:
    needs: test
    runs-on: ubuntu-latest
    steps:
      - name: Check out the repository
        uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'adopt'

      - name: Build JAR
        run: mvn clean package -DskipTests

      - name: Log in to Docker Hub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKER_USERNAME }}
          password: ${{ secrets.DOCKER_PASSWORD }}

      - name: Build and Push Docker Image
        run: |
          docker build -t juanlopezaranzazu388/cliente-service:latest .
          docker push juanlopezaranzazu388/cliente-service:latest

  deploy:
    if: false
    needs: build-and-publish
    runs-on: ubuntu-latest

    steps:
      - name: Check out repository
        uses: actions/checkout@v4
        with:
          fetch-depth: 0

      - name: Verify compose.yaml Exists
        run: |
          if [ ! -f compose.yaml ]; then
          echo "Error: compose.yaml not found!"
          exit 1
          fi

      - name: Create SSH Key File
        run: |
          mkdir -p ~/.ssh
          echo "${{ secrets.EC2_SSH_KEY }}" > ~/.ssh/id_rsa
          chmod 600 ~/.ssh/id_rsa

      - name: Create directory on EC2
        uses: appleboy/ssh-action@v0.1.8
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ubuntu
          key: ${{ secrets.EC2_SSH_KEY }}
          script: |
            mkdir -p ~/app-receiver-iot

      - name: Add Host to known_hosts
        run: |
          ssh-keyscan -H ${{ secrets.EC2_HOST }} >> ~/.ssh/known_hosts

      - name: Copy docker-compose.yml to EC2
        env:
         EC2_HOST: ${{ secrets.EC2_HOST }}
        run: |
          scp -i ~/.ssh/id_rsa ./compose.yaml ubuntu@$EC2_HOST:~/app-receiver-iot/compose.yaml

      - name: Run Docker Compose on EC2
        uses: appleboy/ssh-action@v0.1.8
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ubuntu
          key: ${{ secrets.EC2_SSH_KEY }}
          command_timeout: 10m
          script: |
            cd ~/app-receiver-iot
            sudo docker compose --profile app pull
            sudo docker compose --profile app up -d          
```

## Eventos que lo disparan
- `push` al branch `main`
- `pull_request` (opened, synchronize, reopened)

## Job: test
Ejecuta pruebas unitarias y genera un reporte de cobertura.

- Usa Ubuntu y Java 21 (AdoptOpenJDK).
- Cachea dependencias de Maven.
- Ejecuta `mvn test` y genera reporte Jacoco.
- Sube los resultados de pruebas y cobertura.
- Muestra los resultados en GitHub con `dorny/test-reporter`.

## Job: build-and-publish
Construye y publica la imagen Docker.

- Se ejecuta solo si las pruebas pasan (`needs: test`).
- Compila el proyecto con Maven (`mvn clean package`).
- Inicia sesión en Docker Hub.
- Construye y sube la imagen a Docker Hub (`docker build` y `docker push`).

## Job: deploy
Despliega en un servidor EC2.

- Verifica que `compose.yaml` exista.
- Usa SSH para copiar el archivo a la instancia EC2.
- Ejecuta `docker compose` remotamente para levantar la aplicación.