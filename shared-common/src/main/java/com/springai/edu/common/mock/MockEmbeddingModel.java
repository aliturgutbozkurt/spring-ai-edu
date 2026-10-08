package com.springai.edu.common.mock;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.AbstractEmbeddingModel;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Deterministic MockEmbeddingModel returning fixed-size vector representations.
 * Useful for offline unit testing of RAG pipelines, VectorStore, and document splitters.
 */
public class MockEmbeddingModel extends AbstractEmbeddingModel {

    private final int dimensions;

    public MockEmbeddingModel() {
        this(384);
    }

    public MockEmbeddingModel(int dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        List<Embedding> embeddings = new ArrayList<>();
        List<String> instructions = request.getInstructions();

        for (int i = 0; i < instructions.size(); i++) {
            String text = instructions.get(i);
            float[] vector = generateVector(text, dimensions);
            embeddings.add(new Embedding(vector, i));
        }

        return new EmbeddingResponse(embeddings);
    }

    @Override
    public float[] embed(Document document) {
        return generateVector(document.getText(), dimensions);
    }

    private float[] generateVector(String text, int dim) {
        float[] vector = new float[dim];
        int hash = Math.abs(text.hashCode());
        for (int i = 0; i < dim; i++) {
            // Deterministic positive float between 0.1 and 1.0 to guarantee positive cosine similarity
            vector[i] = (float) (0.1 + 0.9 * Math.abs(Math.sin(hash + i * 0.17)));
        }
        return vector;
    }

    @Override
    public int dimensions() {
        return dimensions;
    }
}
