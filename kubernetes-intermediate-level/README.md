# Kubernetes nivel intermedio: configuración declarativa, secrets y volúmenes

## Crear un deployment y un service en YAML

Crea un archivo llamado `webapp.yaml`

* **Deployment**: se asegura de que haya 2 copias (pods) corriendo la imagen Nginx.
* **Service**: permite que accedamos a los pods desde fuera del clúster, mediante un puerto asignado manualmente (30080).
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: webapp
spec:
  replicas: 2
  selector:
    matchLabels:
      app: webapp
  template:
    metadata:
      labels:
        app: webapp
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: webapp-service
spec:
  selector:
    app: webapp
  type: NodePort
  ports:
    - port: 80
      targetPort: 80
      nodePort: 30080
```

Aplicar el archivo
```sh
kubectl apply -f webapp.yaml
```

Verifica que los recursos se hayan creado
```sh
kubectl get deployments
kubectl get pods
kubectl get services
```

Acceder al navegador
```sh
http://localhost:30080
```

## Externalizar configuraciones con ConfigMap

Crear el ConfigMap
Guarda este contenido en `configmap.yaml`
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: webapp-config
data:
  SALUDO: "¡Hola desde Kubernetes!"
```

Aplica el archivo
```sh
kubectl apply -f configmap.yaml
```

Usar el ConfigMap en el contenedor
Agrega dentro del `containers:` del archivo `webapp.yaml`

```yaml
env:
  - name: SALUDO
    valueFrom:
      configMapKeyRef:
        name: webapp-config
        key: SALUDO
```

Reaplica
```sh
kubectl apply -f webapp.yaml
```

Verifica que el pod reciba la variable
```sh
kubectl get pods
kubectl exec -it <nombre-del-pod> -- printenv SALUDO
```

## Manejar información sensible con Secrets

Crear el Secret
Guarda esto como `secret.yaml`
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: webapp-secret
type: Opaque
data:
  PASSWORD: aGFwcHljb2Rpbmc=  # base64 de "happycoding"
```

Usar el Secret en la aplicación
Aplica
```sh
kubectl apply -f secret.yaml
```

Agrega a la sección de `env` del contenedor
```yaml
- name: PASSWORD
  valueFrom:
    secretKeyRef:
      name: webapp-secret
      key: PASSWORD
```

Reaplice y verifica
```sh
kubectl apply -f webapp.yaml
kubectl get pods
kubectl exec -it <nombre-del-pod> -- printenv PASSWORD
```

## Montar archivos como volúmenes (configuración por archivo)

Crear ConfigMap
Guarda como `config-volume.yaml`
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: html-config
data:
  index.html: |
    <html>
      <body>
        <h1>¡Hola desde un volumen en Kubernetes!</h1>
      </body>
    </html>
```

Aplica
```sh
kubectl apply -f config-volume.yaml
```

Montar el archivo como volumen
Crea `webapp-volumen.yaml`
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: webapp-volumen
spec:
  replicas: 1
  selector:
    matchLabels:
      app: webapp-volumen
  template:
    metadata:
      labels:
        app: webapp-volumen
    spec:
      containers:
      - name: nginx
        image: nginx
        volumeMounts:
        - name: html-volume
          mountPath: /usr/share/nginx/html
      volumes:
      - name: html-volume
        configMap:
          name: html-config
```

Aplica
```sh
kubectl apply -f webapp-volumen.yaml
kubectl expose deployment webapp-volumen --type=NodePort --port=80 --name=webapp-volumen-service
```

## Limpieza de los recursos

Eliminar todo
```sh
kubectl delete -f webapp.yaml
kubectl delete -f configmap.yaml
kubectl delete -f secret.yaml
kubectl delete -f config-volume.yaml
kubectl delete -f webapp-volumen.yaml
kubectl delete service webapp-service
kubectl delete service webapp-volumen-service
```

## Resultados

Comandos para crear deployment y service
![Crear Deployment Service](/kubernetes-intermediate-level/images/crear-deployment-service.png)

Acceder a la aplicación
![Acceder Aplicacion](/kubernetes-intermediate-level/images/acceder-aplicacion.png)

Comandos para externalizar configuraciones con ConfigMap
![ConfigMap](/kubernetes-intermediate-level/images/configmap.png)

Comandos para manejar información sensible con Secrets
![Secrets](/kubernetes-intermediate-level/images/secrets.png)

Comandos para montar archivos como volúmenes
![Volumenes](/kubernetes-intermediate-level/images/volumenes.png)

Acceder a la aplicación HTML
![Acceder Aplicacion HTML](/kubernetes-intermediate-level/images/acceder-aplicacion-html.png)

Comandos para limpieza de los recursos
![Limpieza Recursos](/kubernetes-intermediate-level/images/limpieza-recursos.png)