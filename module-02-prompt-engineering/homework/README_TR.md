# Ödev 02: Akıllı SQL Sorgu ve Açıklama Üreteci (Türkçe)

## Amaç
Spring AI'ın `PromptTemplate` ve `ChatClient` bileşenlerini kullanarak; bir veritabanı şeması ve doğal dil sorusunu girdi olarak alan, prompt enjeksiyonu saldırılarını püskürten, few-shot örneklemeli öğrenme uygulayan ve açıklamalı ANSI SQL üreten tip güvenli bir servis geliştirmek.

## Gereksinimler
1. **Şablon Parametreleştirme**: `{schema}`, `{dialect}` ve `{question}` değişkenlerini `PromptTemplate` ile doldurma.
2. **Güvenlik Koruması**: Kötü niyetli jailbreak veya enjeksiyon (`ignore previous instructions` vb.) girişimlerini tespit edip engelleme.
3. **Few-Shot Örnekleri**: Doğru SQL kalıplarını model yönlendirmesi için prompt'a dinamik ekleme.
4. **Otomatik Testler**: `MockChatModel` ile %100 başarı sağlayan test takımı.
