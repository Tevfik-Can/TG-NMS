#!/bin/bash

# Config
IMAGE_NAME="tcgulbent/tg-nms"
TAG=$(git rev-parse --short HEAD)  # or use a manual version like v1.0.1

# Step 1: Build the Docker image
echo "🔨 Building Docker image: $IMAGE_NAME:$TAG"
docker build -t $IMAGE_NAME:$TAG .

# Step 2: Push the image to Docker Hub
echo "🚀 Pushing image to Docker Hub..."
docker push $IMAGE_NAME:$TAG

# Step 3: Update the Kubernetes deployment
echo "📦 Updating Kubernetes deployment..."
kubectl set image deployment/python-app python-app=$IMAGE_NAME:$TAG

echo "✅ Deployment triggered with image: $IMAGE_NAME:$TAG"
