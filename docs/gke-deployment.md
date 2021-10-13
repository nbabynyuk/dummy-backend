1. Set project ID
   ```
        export PROJECT_ID=****
   ```
2. Configure gke remote registries:
   ``` 
        gcloud auth configure-docker
   ```
3. Tag an image that you want to build for remote GCR repository. Pattern is: gcr.io/<project_id>/<image_name> , 
   for instance
   ```
        docker tag sample-app-backend:0.0.5 gcr.io/$PROJECT_ID/sample-app-backend:0.0.5
   ```
4. Push image to remote container registry
   ```
        docker push gcr.io/$PROJECT_ID/sample-app-backend:0.0.5
    ```
5. Create container registry:
   ``` 
        gcloud container clusters create nbgkesample --num-nodes 2 --machine-type e2-medium
   ```
6. Get credentials for current kubernetes cluster
   ```
    gcloud container clusters get-credentials nbgkesample
   ```
7. Create sample configs and secrets(this step is required for launching particulat dummy backend image):
   ```
        k create configmap sample-app-config --from-literal=SAMPLE-APP-CONFIG-VALUE=****
        k create secret generic app-secret --from-literal=DB_PASSWORD=*****
   ```
8. Deploy app resources:
   ```
        k apply -f k8s/sample-backend-deployment.yaml
        k apply -f k8s/sample-service.yaml
    ```

--- Shuting it down ---
Delete cluster: 
``` 
    gcloud container clusters delete nbgkesample
    gcloud container images delete gcr.io/learning-gcloud-221816/sample-app-backend:0.0.5
```