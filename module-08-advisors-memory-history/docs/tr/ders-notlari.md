---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Modül 08
## Sohbet Danışmanları (Advisors), Bellek Sistemleri ve Konuşma Durumu
### Durumsuz (Stateless) LLM Mimarilerinde Durum Yönetimi

---

## 1. Durumsuz (Stateless) LLM Problemi

- HTTP ve LLM API'leri doğası gereği durumsuzdur (stateless):
  - Her çağrı birbirinden bağımsız değerlendirilir.
  - Önceki konuşma geçmişi istemle (prompt) birlikte gönderilmezse model geçmişi hatırlamaz.
- **Spring AI Çözümü**: `ChatMemory` soyutlaması ve `MessageChatMemoryAdvisor`.

---

## 2. Spring AI ChatMemory Mimarisi

```
[Kullanıcı Mesajı] 
       │
       ▼
[MessageChatMemoryAdvisor] ──► Geçmişi [ChatMemory]'den okur
       │
       ▼
[Geçmiş Eklenmiş LLM Çağrısı]
       │
       ▼
[Model Yanıtı] ──► Yeni turu [ChatMemory]'ye yazar
```

- Bellek sağlayıcıları: `InMemoryChatMemory`, Redis, JDBC, Cassandra.

---

## 3. `conversationId` ile Oturum İzolasyonu

```java
String reply = chatClient.prompt()
    .user(userMessage)
    .advisors(a -> a.param(
        AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,
        sessionId
    ))
    .call()
    .content();
```

- Her kullanıcının konuşma geçmişi diğer kullanıcılardan kesin olarak yalıtılır.

---

## 4. Bağlam Patlamasını Önleme: Kayan Pencere (Sliding Window)

- Sınırlandırılmayan sohbet geçmişi zamanla devasa boyutlara ulaşır:
  - Token maliyetleri katlanır.
  - Modelin bağlam penceresi limiti aşılır.
- **Özel CallAroundAdvisor**: Mesaj sayısı veya token bütçesi aşıldığında en eski mesajları budar veya özetler.

---

## 5. Çıkarımlar ve İlkeler

1. Sohbet durumunu statik değişkenlerde tutmayın; `ChatMemory` kullanın.
2. Her oturumu benzersiz `conversationId` ile izole edin.
3. Token tüketimini sınırlamak ve hafızayı temiz tutmak için kayan pencere (sliding window) mekanizması kurun.
