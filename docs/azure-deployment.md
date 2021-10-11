1. Create a resource group
   ```
   az group create --name aks-demo --location westeurope
   ```
2. Create container registry
   ```
   az acr create --name nbaksdemocr --location westeurope --resource-group aks-demo --sku basic
   ```
3. Build an image and publish it to the container registry
   ```
   az acr build --image nb-dummy-backend:0.0.5 --registry nbaksdemocr --file Dockerfile .
   ```
   Or as an alternative use combination of commands docker build/docker tag. Actually docker build and docker tag is preferred,   since the command above doesn't create an image on you local machine.
4. Create cluster
   ```
   az aks create -g aks-demo \
        -n nbaksdemo \ 
        --node-vm-size Standard_B2s \ 
        --node-count 2
   ```
5. Allow access to container registry from the cluster
   ```
   az aks update -n nbaksdemo -g aks-demo --attach-acr nbaksdemocr
   ```
   Alternatice: Use az role assignment create
6. Get context
    ```
       az aks get-credentials -n nbaksdemo -g aks-demo
    ```   
7. Create sample configs and secrets(this step is required for launching particulat dummy backend image):
   ```
    k create configmap sample-app-config --from-literal=SAMPLE-APP-CONFIG-VALUE=nazar1config
    k create secret generic app-secret --from-literal=DB_PASSWORD=super_secret_value_here
   ```
8. Deploy app resources:
   ```
   k apply -f k8s/sample-backend-deployment.yaml
   k apply -f k8s/sample-service.yaml
   ```

-- Shutting it down --
```
az aks delete -g aks-demo -n nbaksdemo
az acr repository delete --name nbaksdemocr --repository nb-dummy-backend
az group delete -g aks-demo
```