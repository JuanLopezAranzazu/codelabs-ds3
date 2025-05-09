# Kubernetes nivel intermedio: configuración declarativa, secrets y volúmenes

## Despliegue de múltiples servicios

En esta sección se replicara en Kubernetes una arquitectura común de microservicios compuesta por:

* Dos bases de datos PostgreSQL (`db1`, `db2`)
* Dos servicios Java (`pais-service`, `auth-service`)
* Una red compartida
* Configuración mediante variables de entorno

## Definir el namespace y contexto

Definir namespace
Crea `namespace.yaml`
```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: auth-demo
```

Aplica el namespace
```sh
kubectl apply -f namespace.yaml
kubectl config set-context --current --namespace=auth-demo
```

## Definir las bases de datos

Crea `db1.yaml`
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: db1-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 500Mi
---
apiVersion: v1
kind: Service
metadata:
  name: db1
spec:
  ports:
    - port: 5432
  selector:
    app: db1
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: db1
spec:
  selector:
    matchLabels:
      app: db1
  template:
    metadata:
      labels:
        app: db1
    spec:
      containers:
        - name: postgres
          image: postgres:latest
          ports:
            - containerPort: 5432
          env:
            - name: POSTGRES_DB
              value: curso_springboot
            - name: POSTGRES_USER
              value: devdb
            - name: POSTGRES_PASSWORD
              value: a1b2c3d4
          volumeMounts:
            - mountPath: /var/lib/postgresql/data
              name: db1-storage
      volumes:
        - name: db1-storage
          persistentVolumeClaim:
            claimName: db1-pvc
```

Crea `db2.yaml`
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: db2-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 500Mi
---
apiVersion: v1
kind: Service
metadata:
  name: db2
spec:
  ports:
    - port: 5432
  selector:
    app: db2
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: db2
spec:
  selector:
    matchLabels:
      app: db2
  template:
    metadata:
      labels:
        app: db2
    spec:
      containers:
        - name: postgres
          image: postgres:latest
          ports:
            - containerPort: 5432
          env:
            - name: POSTGRES_DB
              value: curso_springboot
            - name: POSTGRES_USER
              value: devdb
            - name: POSTGRES_PASSWORD
              value: a1b2c3d4
          volumeMounts:
            - mountPath: /var/lib/postgresql/data
              name: db2-storage
      volumes:
        - name: db2-storage
          persistentVolumeClaim:
            claimName: db2-pvc
```

## Definir los servicios pais y auth

Crea `pais-service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: pais-service
spec:
  ports:
    - port: 8080
  selector:
    app: pais-service
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: pais-service
spec:
  selector:
    matchLabels:
      app: pais-service
  template:
    metadata:
      labels:
        app: pais-service
    spec:
      containers:
        - name: pais
          image: alvarosalazar/pais-service:latest
          ports:
            - containerPort: 8080
          env:
            - name: DB_URL
              value: jdbc:postgresql://db1:5432/curso_springboot
            - name: DB_USERNAME
              value: devdb
            - name: DB_PASSWORD
              value: a1b2c3d4
            - name: JPA_DDL
              value: create-drop
          readinessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 10
            periodSeconds: 5
```

Crea `auth-service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: auth-service
spec:
  ports:
    - port: 8080
  selector:
    app: auth-service
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: auth-service
spec:
  selector:
    matchLabels:
      app: auth-service
  template:
    metadata:
      labels:
        app: auth-service
    spec:
      containers:
        - name: pais
          image: alvarosalazar/auth-service:latest
          ports:
            - containerPort: 8080
          env:
            - name: DB_URL
              value: jdbc:postgresql://db2:5432/curso_springboot
            - name: DB_USERNAME
              value: devdb
            - name: DB_PASSWORD
              value: a1b2c3d4
            - name: JPA_DDL
              value: create-drop
          readinessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 10
            periodSeconds: 5
```

## Aplicar todos los recursos

Aplica
```sh
kubectl apply -f db1.yaml
kubectl apply -f db2.yaml
kubectl apply -f pais-service.yaml
kubectl apply -f auth-service.yaml
```

Verifica
```sh
kubectl get pods
kubectl get svc
```

Acceso desde el navegador
```sh
kubectl port-forward svc/pais-service 8082:8080
kubectl port-forward svc/auth-service 8081:8080
```

Accede
```sh
http://localhost:8081/
http://localhost:8082/
```

## Resultados

Comandos para crear los recursos
![Crear Recursos](/k8s-auth-demo/images/crear-recursos.png)

Comandos para exponer los servicios
![Crear Recursos](/k8s-auth-demo/images/exponer-servicios.png)