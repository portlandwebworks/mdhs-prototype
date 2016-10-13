# MDHS Kubernetes Demo 

Kubernetes is an open-source system for automating deployment, scaling, and management of containerized applications. This contains a fully functional provisioning code that can be used to deploy the published Docker images to a high-availability cluster in the Google Compute Engine platform.

## Notes
- High Availability
- Auto Scaling
- Docker based containers 
- Platform Independent 
- Open Source
- This example utilizes an imbedded H2SQL database
- Can be easily pointed to a high availability database by leveraging encrypted secrets

### Setup Google Cloud Cluster
This step requires a Google Cloud Compute account and the Google Cloud SDK.

```
gcloud auth login

gcloud config set project GCE_PROJECT_ID
gcloud config set compute/zone us-central1-c
gcloud container clusters create GCE_PROJECT_ID
```

### Provision the Cluster
Create the cluster

```
kubectl create -f mdhs-prototype/
```

### Kubernetes Secret (optional)
The following is used to configure the kubernetes secret that stores sensitive information. This is encrypted in the Kubernetes platform and made available to containers when they are deployed.

This is an example of how to configure the secret for the database connection.

```
kubectl create secret generic mdhs \
--from-literal=spring.datasource.username=USERNAME \
--from-literal=spring.datasource.password=PASSWORD \
--from-literal=spring.datasource.url=jdbc:postgresql://DB_HOSTNAME:3306/DB_NAME
```

These secrets can be passed to the kubernetes deployment to override the application databse connection.

```
        env:
          - name: SPRING_DATASOURCE_USERNAME
            valueFrom:
              secretKeyRef:
                name: chhs
                key: spring.datasource.username
          - name: SPRING_DATASOURCE_PASSWORD
            valueFrom:
              secretKeyRef:
                name: chhs
                key: spring.datasource.password
          - name: SPRING_DATASOURCE_URL
            valueFrom:
              secretKeyRef:
                name: chhs
                key: spring.datasource.url
```


### HTTPS Load Balancer (optional)
This is an example of how to configure the secret for the HTTPS load balancer. You need need a Certificate and Private Key file as indicated.

```
echo "
apiVersion: v1
kind: Secret
metadata:
  name: tls
  namespace: default
data:
  tls.crt: `base64 -i ssl/domain.crt`
  tls.key: `base64 -i ssl/domain.key`
type: Opaque
" | kubectl create -f -
```

Firewall rule for load balancer

```
gcloud compute firewall-rules create allow-130-211-0-0-22 \
   --source-ranges 130.211.0.0/22 \
   --allow tcp:30000-32767
```

##### Namespaces
Create a production environment in a different namespace which is completely isolated from other environments.

```
kubectl create -f prod-namespace.yml
kubectl create -f mdhs-prototype/ --namespace=prod
```

The above command can also be used to provision stage, qa and other environments to meet the project requirements. You will also need to create the secret in the production namespace using `--namespace`.


### Docker Compose Demo
The Kubernetes platform leverages [Docker] (https://www.docker.com/what-docker) containers for all of it's deployments. This is a quick demo of the environment that can be setup locally using Docker Compose. This will launch a local environment using the same publically accessible Docker images used to power the cloud hosted environment. This example also demonstrates externalized configuration by overriding the default H2SQL Database with a PostgreSQL databse. 

From the current directory, run the following and visit [http://localhost:8080/](http://localhost:8080/).

```
docker-compose up
```

Please see the `docker-compose.yml` in the current directory to see how this works.
