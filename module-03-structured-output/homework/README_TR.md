# Ödev 03: Özgeçmiş (CV) Yapılandırılmış Veri Çıkarma Servisi (Türkçe)

## Amaç
Spring AI'ın `BeanOutputConverter<CandidateProfile>` bileşenini kullanarak serbest metin formatındaki özgeçmişleri değişmez (immutable) Java `record` sınıflarına dönüştüren tip güvenli bir servis geliştirmek.

## Gereksinimler
1. `BeanOutputConverter<CandidateProfile>` kullanarak modele JSON şemasını talimat olarak iletme.
2. Model çıktısını JSON'dan doğrudan `CandidateProfile` nesnesine dönüştürme.
3. Boş girdi ve şema ayrıştırma durumlarını ele alma.
4. Tüm JUnit 5 testlerini başarıyla geçme.
