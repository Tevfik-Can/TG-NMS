# TG-NMS

## Overview
TG-NMS is now a Python Flask web application with a PostgreSQL backend, containerized with Docker and deployable to Kubernetes. This replaces the previous Node.js/Express implementation.

---

## Project Structure

```
TG-NMS/
├── app.py                  # Main Python application (Flask)
├── requirements.txt        # Python dependencies
├── Dockerfile              # Docker build instructions
├── templates/              # HTML templates (Jinja2)
│   ├── layout.html
│   └── index.html
├── static/                 # Static files (CSS, JS, images)
│   └── style.css
└── k8s/                    # Kubernetes manifests
    ├── python-deployment.yaml
    ├── python-service.yaml
    ├── postgres-deployment.yaml
    ├── postgres-service.yaml
    └── postgres-pvc.yaml
```

---


## Key Changes from Node.js to Python
### Initially planned on using node js but ultimatetly changed to python flask
- **Backend:** Switched from Node.js (Express) to Python (Flask)
- **Templates:** Switched from EJS to Jinja2 (Flask's default)
- **Database Adapter:** Switched from `pg` (Node.js) to `psycopg2-binary` (Python)
- **App Entrypoint:** Now `app.py` instead of `src/app.js`
- **Dependencies:** Now managed in `requirements.txt` instead of `package.json`
- **Development Workflow:** Use Flask's debug mode for instant reloads
- **Dockerfile:** Now uses Python base image and pip

---

## Development Workflow (Hot Reload)

### Local Development (Recommended)

1. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
2. Run Flask in debug mode:
   ```bash
   export FLASK_ENV=development
   flask run
   # or
   python app.py
   ```
3. Any code change will auto-reload the server.

### Docker Development (with Hot Reload)

1. Build the image:
   ```bash
   docker build -t tg-nms-python:dev .
   ```
2. Run with volume mount for live reload:
   ```bash
   docker run -it --rm -v $(pwd):/app -p 3000:3000 -e FLASK_ENV=development tg-nms-python:dev
   ```

---

## Production Workflow (Docker/Kubernetes)

1. Build and tag the image:
   ```bash
   docker build -t your-dockerhub-username/tg-nms-python:latest .
   docker push your-dockerhub-username/tg-nms-python:latest
   ```
2. Update your Kubernetes deployment YAML to use the new image:
   ```yaml
   containers:
     - name: python-app
       image: your-dockerhub-username/tg-nms-python:latest
       ports:
         - containerPort: 3000
   ```
3. Apply the manifests:
   ```bash
   kubectl apply -f k8s/
   ```

---

## Environment Variables

The app uses the following environment variables (set in Kubernetes or locally):
- `POSTGRES_HOST` (default: `postgres`)
- `POSTGRES_PORT` (default: `5432`)
- `POSTGRES_DB` (default: `postgres`)
- `POSTGRES_USER` (default: `postgres`)
- `POSTGRES_PASSWORD` (default: `postgres`)

---

## Database Initialization

The app will automatically create the `test_items` table if it does not exist. You can add, view, and delete items from the web GUI.

---

## Troubleshooting

- **CrashLoopBackOff:** Check pod logs with `kubectl logs <pod-name>` for Python errors or database connection issues.
- **Hot Reload Not Working:** Make sure you are running in development mode and using volume mounts if in Docker.
- **Database Connection Issues:** Ensure the PostgreSQL service is running and environment variables are set correctly.

---

## License
MIT 