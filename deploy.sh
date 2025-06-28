#!/bin/bash

# Way to use it: .\deploy.sh {UNIQUE NUMBER}  

# Config
IMAGE_NAME="tcgulbent/tg-nms"
TAG=$(git rev-parse --short HEAD)  # or use a manual version like v1.0.1

# Get UNIQUE_NUM from first argument, or default to empty string if none provided
UNIQUE_NUM="${1:-}"
echo "Using tag: $TAG$UNIQUE_NUM"

# Step 1: Build the Docker image
echo "🔨 Building Docker image: $IMAGE_NAME:$TAG$UNIQUE_NUM"
docker build -t $IMAGE_NAME:$TAG$UNIQUE_NUM .

# Step 2: Push the image to Docker Hub
echo "🚀 Pushing image to Docker Hub..."
docker push $IMAGE_NAME:$TAG$UNIQUE_NUM

# Step 3: Update the Kubernetes deployment
echo "📦 Updating Kubernetes deployment..."
kubectl set image deployment/python-app python-app=$IMAGE_NAME:$TAG$UNIQUE_NUM

echo "✅ Deployment triggered with image: $IMAGE_NAME:$TAG$UNIQUE_NUM"
