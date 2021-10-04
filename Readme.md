In order to run kubernetes part:

**Build a project**
```
gradle clean build
```

**Build docker image**
```
docker build . -t sample-app-backend:0.0.3
```

**Create a config map**
```
k create configmap sample-app-config --from-env-file kubernetes/app-sample.env
```

**Create secret**
```
k create secret generic app-secret --from-literal=DB_PASSWORD=qaz123
```
**Run kubernets config**
```
k apply -f kubernetes/sample-backend-deployment.yaml
```