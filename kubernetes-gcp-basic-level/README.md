# Codelab Kubernetes GCP (nivel básico)

## Despliegue completo
Aplica los recursos en orden:
```sh
kubectl apply -f pais-deployment.yaml
kubectl apply -f auth-deployment.yaml
kubectl apply -f backendconfig.yaml
kubectl apply -f ingress.yaml
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