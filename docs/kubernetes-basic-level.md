# Introducción práctica a kubernetes (nivel básico)

## Preparar el entorno

Docker Desktop

1. Abre Docker Desktop.
2. Ve a Settings > Kubernetes.
3. Activa "Enable Kubernetes".
4. Espera hasta que aparezca "Kubernetes is running"

Verificar el clúster
```sh
kubectl get nodes
```


## Desplegar tu primera aplicación

Crear un deployment con nginx
```sh
kubectl create deployment nginx-deploy --image=nginx
```

Verifica que el pod se esté ejecutando
```sh
kubectl get pods
```

Exponer el servicio
```sh
kubectl expose deployment nginx-deploy --type=NodePort --port=80
```

Revisa el servicio
```sh
kubectl get services
```

Acceder desde el navegador
```sh
http://localhost:<puerto-nodeport>
```

## Escalar la aplicación

Aumenta el número de réplicas a 3
```sh
kubectl scale deployment nginx-deploy --replicas=3
kubectl get pods
```

## Eliminar todo

Limpia los recursos creados
```sh
kubectl delete service nginx-deploy
kubectl delete deployment nginx-deploy
```

## Resultados

Comandos para desplegar la aplicación
![Desplegar Aplicacion](/docs/images/desplegar-aplicacion.png)

Acceder a la aplicación
![Acceder Aplicacion](/docs/images/acceder-aplicacion.png)

Comandos para escalar la aplicación
![Escalar Aplicacion](/docs/images/escalar-aplicacion.png)

Comandos para eliminar todo
![Eliminar Aplicacion](/docs/images/eliminar-aplicacion.png)