# TG-NMS

## Overview
TG-NMS is a Python Flask–based network management system backed by PostgreSQL, containerized with Docker, and deployable to Kubernetes. The system now includes a Java-based SNMP server, REST API layer, and Kafka publisher subsystem that will power all real network interactions. Placeholder implementations exist today and will be replaced with production-ready code as the system is integrated with an actual network topology.

---

## Project Structure

```
TG-NMS/
├── app.py                      # Main Python Flask application
├── requirements.txt            # Python dependencies
├── Dockerfile                  # Docker build instructions
├── templates/                  # HTML templates (Jinja2)
│   ├── layout.html
│   └── index.html
├── static/                     # Static files (CSS, JS)
│   └── style.css
├── java-snmp-server/           # Java subsystem for network operations
│   ├── src/main/java/
│   │   ├── snmp               # SNMP handler
│   │   ├── api                # REST API
│   │   └── kafka              # Kafka publisher
│   ├── pom.xml                 # Maven build configuration
│   └── README.md               # Java component documentation
└── k8s/                        # Kubernetes manifests
    ├── python-deployment.yaml
    ├── python-service.yaml
    ├── postgres-deployment.yaml
    ├── postgres-service.yaml
    └── postgres-pvc.yaml
```

---

## Java SNMP Server and Network Pipeline

A new `java-snmp-server/` directory has been introduced to host the network-side services of TG-NMS. This subsystem will evolve into the bridge between TG-NMS and real networking devices.

### Components

#### SNMP Handler (Placeholder)
- Will be replaced with a complete SNMP engine capable of polling devices, receiving traps, and interacting with real routers/switches.  
- Will integrate directly with a physical or virtual network topology.

#### Kafka Publisher (Placeholder)
- Will publish telemetry, traps, and device-state updates to Kafka topics.  
- Python Flask backend will subscribe to these topics for real-time updates.

#### REST API (Placeholder)
- Will expose device metrics and SNMP-derived state to the Python UI.  
- Will act as a central communication layer between Flask and the Java network services.

### Future Integration
Once production implementations are added:

- TG-NMS will pull live data from actual network devices.  
- Kafka will act as the event backbone.  
- The REST API will serve device state to the frontend.  
- The system will function as a true NMS, not just a CRUD interface.

---

## Development Workflow (Hot Reload)

### Python (Flask)

**1. Install dependencies**
```bash
pip install -r requirements.txt
```

**2. Run Flask in development mode**
```bash
export FLASK_ENV=development
flask run
# or
python app.py
```

**3. Hot reload works automatically during development.**

---

### Docker-Based Development (Hot Reload)

**1. Build**
```bash
docker build -t tg-nms-python:dev .
```

**2. Run with volume mount**
```bash
docker run -it --rm \
  -v $(pwd):/app \
  -p 3000:3000 \
  -e FLASK_ENV=development \
  tg-nms-python:dev
```

---

### Java Component Development

```bash
cd java-snmp-server
mvn clean package
java -jar target/java-snmp-server.jar
```

*Docker and Kubernetes manifests for the Java subsystem can be added later.*

---

## Production Workflow (Docker/Kubernetes)

### Build and Push
```bash
docker build -t your-dockerhub-username/tg-nms-python:latest .
docker push your-dockerhub-username/tg-nms-python:latest
```

### Update the Kubernetes Deployment
```yaml
containers:
  - name: python-app
    image: your-dockerhub-username/tg-nms-python:latest
    ports:
      - containerPort: 3000
```

### Deploy
```bash
kubectl apply -f k8s/
```

---

## Environment Variables

Environment variables required by the Flask backend:

- `POSTGRES_HOST` (default: `postgres`)  
- `POSTGRES_PORT` (default: `5432`)  
- `POSTGRES_DB` (default: `postgres`)  
- `POSTGRES_USER` (default: `postgres`)  
- `POSTGRES_PASSWORD` (default: `postgres`)  

Future Java/Kafka environment variables will include:  
- `KAFKA_BROKER_URL`  
- `SNMP_LISTEN_PORT`  
- `JAVA_API_PORT`

---

## Database Initialization

The Flask backend automatically creates the `test_items` table if it does not already exist. Users can add, view, and delete entries through the browser interface.

---

## Troubleshooting

### Python
- **CrashLoopBackOff:**  
  Check logs:  
  ```bash
  kubectl logs <pod-name>
  ```

- **Hot reload not working:**  
  Ensure `FLASK_ENV=development` and Docker volume mounts are correctly set.

- **Database connection issues:**  
  Confirm PostgreSQL service reachability and environment variable configuration.

### Java
- **SNMP service not responding:**  
  Ensure required ports are exposed and Kafka is reachable.

- **Kafka publish errors:**  
  Verify broker connectivity and topic existence.

---
