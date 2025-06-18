# Codelab Kubernetes GCP (nivel básico)

## Creación de la red virtual
```sh
gcloud compute networks create <RED> --project=<ID-PROYECTO> --subnet-mode=custom --mtu=<MTU> --bgp-routing-mode=regional
```

## Subred para kubernetes
```sh
gcloud compute networks subnets create <NOMBRE-SUBRED> --range=<RANGO-IP> --network=<RED-PADRE> --region=<REGION> --project=<ID-PROYECTO>
```

## Despliegue completo
Aplica los recursos en orden:
```sh
kubectl apply -f db1.yaml
kubectl apply -f db2.yaml
kubectl apply -f pais-deployment.yaml
kubectl apply -f auth-deployment.yaml
kubectl apply -f backendconfig.yaml
kubectl apply -f ingress.yaml
```

## Obtener IP pública del Ingress
```sh
kubectl get ingress app-ingress
```

## Pruebas de funcionamiento
```sh
curl -i http://<ip>/api/pais-service/hola/mundo
curl -i http://<ip>/api/pais-service/pais-actuator/health
```

## Describir el servicio ingress
Usa este comando para ver el estado de los servicios vistos por el ingress.
```sh
kubectl describe ingress app-ingress
```

## Verificar si estan corriendo pods de un servicio
```sh
kubectl get pods -l app=pais-service
kubectl get pods -l app=auth-service
```

## Verifica los BackendConfig
```sh
kubectl describe backendconfig pais-backendconfig
kubectl describe backendconfig auth-backendconfig
```

## Reiniciar el despliegue de un servicio
Use este comando cuando haya actualizado la imagen del servicio. Espere unos minutos a que el nuevo pod reemplace al anterior.
```sh
kubectl rollout restart deployment pais-service
kubectl rollout restart deployment auth-service
```

## Eliminar recursos
```sh
kubectl delete -f db1.yaml
kubectl delete -f db2.yaml
kubectl delete -f pais-deployment.yaml
kubectl delete -f auth-deployment.yaml
kubectl delete -f backendconfig.yaml
kubectl delete -f ingress.yaml
```

## Resultados

### Creación de la red virtual
![Red Virtual](/kubernetes-gcp-basic-level/images/crear-red-virtual.png)

### Subred para kubernetes
![Subred Kubernetes](/kubernetes-gcp-basic-level/images/crear-subred-kubernetes.png)

### Despliegue del clúster
![Despliegue Clúster](/kubernetes-gcp-basic-level/images/despliegue-cluster.png)

### Conexión del clúster
![Conexión Clúster](/kubernetes-gcp-basic-level/images/conexion-cluster.png)

### Despliegue del backend
![Despliegue Backend](/kubernetes-gcp-basic-level/images/despliegue-backend.png)

### Pruebas de funcionamiento
![Pruebas Funcionamiento](/kubernetes-gcp-basic-level/images/pruebas-funcionamiento.png)
