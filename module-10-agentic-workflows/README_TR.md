# Modül 10: Ajan Yapay Zeka (Agentic AI) ve Otonom Çoklu Ajan İş Akışları

Spring AI Eğitim Serisi **Modül 10**'a hoş geldiniz. Bu modülde Java ve Spring AI ile otonom akıl yürütme döngüleri (ReAct), süpervizör-işçi ajan takımları ve insan onay mekanizmaları geliştirmeyi öğreneceksiniz.

## Ana Özellikler
- **ReAct Geri Bildirim Döngüleri**: Otonom Düşünce -> Eylem -> Gözlem yürütme motoru.
- **Süpervizör-İşçi Ajan Takımları**: Görev ayrıştırma, alt ajanlara delegasyon ve çıktı sentezleme.
- **Operasyonel Sınırlar**: Maksimum adım sınırlaması ve sonsuz döngü engelleme.
- **İnsan Onay Kapıları (Human-in-the-Loop)**: Kritik eylemler öncesi insan denetimi.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-10-agentic-workflows -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-10-agentic-workflows/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-10-agentic-workflows/homework/solution/pom.xml
```
