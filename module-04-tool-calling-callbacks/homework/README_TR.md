# Ödev 04: Otonom Müşteri Destek ve Talep Değerlendirme Ajanı (Türkçe)

## Amaç
Spring AI'ın `@Tool` anotasyonu ve `ChatClient.tools(...)` yeteneğini kullanarak; sipariş sorgulama ve iade uygunluk hesaplama araçlarını otonom olarak çağıran bir müşteri destek sistemi geliştirmek.

## Gereksinimler
1. `checkEligibility(String orderId, double amount)` metodunu `@Tool` olarak işaretleme.
2. Aracı `ChatClient` ile ilişkilendirme.
3. JUnit 5 testlerini %100 başarıyla tamamlama.
