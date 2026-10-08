---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Modül 06
## RAG Bölüm 2: İleri Düzey Arama, Yeniden Sıralama (Reranking) ve Değerlendirme Metrikleri
### Üretim Standardında Kurumsal RAG Sistemleri

---

## 1. Basit (Naive) RAG Neden Üretimde Yetersiz Kalır?

- Vektör araması anlamsal olarak benzer ancak olgusal olarak yanlış parçalar getirebilir:
  - Yanlış yargı bölgesi (jurisdiction) veya geçerliliğini yitirmiş eski bir sözleşme yılı.
  - Madde numaraları ve tam kod aramalarında zayıf kalır.
- **Çözüm**: Hibrit Arama + Üst Veri Filtreleme (Metadata Filtering) + Bağlamsal Yeniden Sıralama (Reranking).

---

## 2. Spring AI'da Üst Veri Filtreleme

```java
SearchRequest request = SearchRequest.builder()
    .query("fesih ihbar süresi")
    .topK(10)
    .filterExpression("jurisdiction == 'TR' AND effectiveYear >= 2024")
    .build();

List<Document> candidates = vectorStore.similaritySearch(request);
```

- Filtre ifadeleri çok kiracılı (multi-tenant) sistemlerde veri sızıntılarını önler.

---

## 3. İki Kademeli Arama ve Sıralama (Two-Stage Retrieval)

```
[Sorgu] -> Aşama 1: Vektör Arama (Geniş 20 aday parça)
        -> Aşama 2: Yeniden Sıralayıcı (En hassas 3 parça)
        -> Aşama 3: LLM Yanıt Üretimi
```

- Milyonlarca dokümanı pahalı modellere sokmadan yüksek doğruluk (Precision@K) sağlar.

---

## 4. RAG Üçlüsü (RAG Triad) Değerlendirme Metrikleri

```
             [ Kullanıcı Sorgusu ]
               /               \
              /                 \
   Bağlam Uygunluğu        Yanıt Uygunluğu
    (Context Rel)           (Answer Rel)
            /                     \
           v                       v
     [ Bağlam ] <---------- [ LLM Yanıtı ]
              Doğrulanabilirlik
               (Groundedness)
```

1. **Bağlam Uygunluğu**: İstenen bilgi doğru çekildi mi?
2. **Doğrulanabilirlik**: Yanıt yalnızca verilen bağlama mı dayanıyor?
3. **Yanıt Uygunluğu**: Yanıt kullanıcının sorusunu doğrudan çözüyor mu?

---

## 5. Çıkarımlar ve İlkeler

1. Arama uzayını daraltmak için önce üst verilerle filtreleyin.
2. Aday parçaları LLM'e göndermeden önce yeniden puanlayın (rerank).
3. RAG Triad metrikleriyle halüsinasyonları üretimde otomatik denetleyin.
