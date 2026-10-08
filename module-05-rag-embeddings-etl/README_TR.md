# Modül 05: RAG Bölüm 1: Vektör Gömme, Vektör Veritabanları ve Doküman ETL

Spring AI Eğitim Serisi **Modül 05**'e hoş geldiniz. Bu modülde Java ile kurumsal düzeyde Bilgi Artırımlı Üretim (RAG) mimarisini baştan sona öğreneceksiniz.

## Ana Özellikler
- **Doküman ETL Hattı**: `TokenTextSplitter` ile akıllı metin parçalama ve üst veri ilişkilendirme.
- **Vektör Gömme (Embeddings)**: Spring AI `EmbeddingModel` ile metinleri anlamsal uzaya taşıma.
- **Vektör Veritabanı Soyutlaması**: `SimpleVectorStore` ve `PgVectorStore` üzerinden benzerlik aramaları.
- **Eşik ve Üst Veri Filtreleme**: Kosinüs benzerliği eşikleri ve departman/kategori filtreleri.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-05-rag-embeddings-etl -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-05-rag-embeddings-etl/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-05-rag-embeddings-etl/homework/solution/pom.xml
```
