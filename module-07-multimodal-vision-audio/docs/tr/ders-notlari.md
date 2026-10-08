---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Modül 07
## Çok Modlu (Multimodal) Yapay Zeka: Görme (Vision), Ses, Konuşma ve Doküman OCR
### Spring AI ile Çapraz Modlu Kurumsal Mühendislik

---

## 1. Çok Modlu (Multimodal) Yapay Zeka Nedir?

- Klasik LLM'ler yalnızca metin tokenları ile çalışır.
- **Çok Modlu Modeller** (GPT-4o, Claude 3.5 Sonnet, LLaVA, Whisper) metin istemlerinin yanında resim, ses ve dokümanları doğrudan işleyebilir.
- Spring AI bu yetenekleri `org.springframework.ai.model.Media` soyutlamasıyla tek bir çatı altında toplar.

---

## 2. Spring AI Media Soyutlaması

```java
// Bayt dizisi veya URL üzerinden Media nesnesi oluşturma
Media receiptImage = new Media(
    MimeTypeUtils.IMAGE_JPEG,
    new ByteArrayResource(imageBytes)
);

// Resmi doğrudan kullanıcı istemine (prompt) bağlama
AuditReport report = chatClient.prompt()
    .user(u -> u
        .text("Bu ambar fişini incele ve tutarsızlıkları listele.")
        .media(receiptImage)
    )
    .call()
    .entity(AuditReport.class);
```

---

## 3. Otomatik Doküman OCR ve Yapılandırılmış Çıkarım

- Çok modlu görme modellerini `BeanOutputConverter<T>` ile birleştirin:
  - Fiş ve fatura satırlarını doğrudan tip güvenli Java 27 kayıtlarına (records) dönüştürün.
  - Kırılgan regex ve geleneksel koordinat tabanlı OCR zahmetinden kurtulun.

---

## 4. Ses Metne Dönüştürme (STT) ve Konuşma Sentezi (TTS)

```
[Denetçi Sesli Notu (.wav/.mp3)]
               │
               ▼
[Spring AI Whisper / Ses Modeli]
               │
               ▼
[Yapılandırılmış Metin Dökümü]
               │
               ▼
[Eyleme Dönüştürülebilir Stok Düzeltmeleri]
```

---

## 5. Çıkarımlar ve Mimari İlkeler

1. Görselleri göndermeden önce boyutlarını kontrol edin ve optimize edin.
2. MIME türlerini kesinleştirin (`image/jpeg`, `image/png`, `audio/wav`).
3. Görselden okunan miktarları her zaman kurumsal veritabanı kayıtlarıyla otomatik karşılaştırın.
