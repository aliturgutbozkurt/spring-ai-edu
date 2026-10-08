# Specification: Module 07 - Multimodal AI: Vision, Audio, Speech & Document OCR

## 1. Overview
Module 07 explores multimodal generative AI with Spring AI. Students learn to process images (OCR, document scanning, visual inspection) using Spring AI's `Media` abstraction, and work with audio inputs and transcription workflows.

## 2. Learning Objectives
1. Understand Spring AI's `org.springframework.ai.model.Media` and multimodal prompt construction.
2. Build an automated receipt and invoice inspection engine returning structured line items.
3. Integrate audio transcription patterns (Whisper speech-to-text) with structured extraction.
4. Handle media encoding (base64, byte arrays, MimeTypes) safely.
5. Provide deterministic unit tests using Mock multimodal payloads.

## 3. Architecture & Components
- **`MediaPayload`**: Record wrapping byte array, `MimeType`, and filename.
- **`InventoryAuditReport`**: Structured record parsed from image + text prompt (items, totals, supplier, timestamp).
- **`MultimodalVisionService`**: Executes multimodal `ChatClient` prompts passing user text and image `Media`.
- **`AudioInspectionService`**: Transcribes voice memos and extracts audit findings.
- **`MultimodalAuditController`**: REST endpoint accepting images and audio clips for automated auditing.

## 4. Quality Gates
- Vision prompt formatting tests.
- Structured media response parsing tests.
- Offline tests with mock media payloads.
- Homework starter with failing stubs; solution with 100% passing tests.
- Bilingual lesson notes and Marp compiled PDFs.
