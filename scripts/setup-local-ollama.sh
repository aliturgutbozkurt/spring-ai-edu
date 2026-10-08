#!/usr/bin/env bash
set -euo pipefail

OLLAMA_HOST="${OLLAMA_HOST:-http://localhost:11434}"

echo "=========================================================="
echo " Spring AI Course: Setting up Local Ollama Models"
echo " Target Host: ${OLLAMA_HOST}"
echo "=========================================================="

# Check if Ollama is accessible
echo "Checking Ollama connectivity..."
max_retries=10
count=0
until curl -s "${OLLAMA_HOST}/api/tags" > /dev/null || [ $count -eq $max_retries ]; do
    echo "Waiting for Ollama to become available at ${OLLAMA_HOST}... ($((count+1))/$max_retries)"
    sleep 3
    count=$((count+1))
done

if [ $count -eq $max_retries ]; then
    echo "Warning: Ollama is not responding at ${OLLAMA_HOST}."
    echo "If running via Docker, make sure you ran: docker compose up -d"
    echo "If running natively, ensure 'ollama serve' is running."
    exit 1
fi

echo "Ollama is online!"

pull_model() {
    local model_name="$1"
    echo "Pulling model: ${model_name}..."
    curl -s -X POST "${OLLAMA_HOST}/api/pull" -d "{\"name\": \"${model_name}\"}" | grep -o '{"status":[^}]*}' || true
    echo "Model '${model_name}' successfully pulled and ready!"
}

# Pull recommended lightweight LLM & Embedding models
pull_model "llama3.2"
pull_model "nomic-embed-text"

echo "=========================================================="
echo " All local models are ready for Spring AI zero-cost labs!"
echo "=========================================================="
