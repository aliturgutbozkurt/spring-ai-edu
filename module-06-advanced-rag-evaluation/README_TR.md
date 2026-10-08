# Modül 06: RAG Bölüm 2: İleri Düzey Arama, Yeniden Sıralama (Reranking) ve Değerlendirme Metrikleri

Spring AI Eğitim Serisi **Modül 06**'ya hoş geldiniz. Bu modülde üretim standartlarında RAG mimarilerini, üst veri filtrelemeyi, bağlamsal yeniden sıralamayı ve RAG Üçlüsü (RAG Triad) denetimlerini öğreneceksiniz.

## Ana Özellikler
- **Üst Veri Filtreli Arama**: Yargı bölgesi ve çok kiracılı sınırlar için SQL benzeri filtre ifadeleri.
- **Bağlamsal Yeniden Sıralama (Reranking)**: Aday parçaları sorgu niyeti ve terim yoğunluğuna göre yeniden puanlama.
- **RAG Üçlüsü Otomatik Değerlendirme**: Bağlam Uygunluğu, Doğrulanabilirlik ve Yanıt Uygunluğu metrikleri.
- **Sözleşme Analizi & Uyumluluk Denetimi**: Hukuki sözleşme maddelerinde mevzuat kontrolü.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-06-advanced-rag-evaluation -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-06-advanced-rag-evaluation/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-06-advanced-rag-evaluation/homework/solution/pom.xml
```
