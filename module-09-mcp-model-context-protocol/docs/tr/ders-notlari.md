---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Modül 09
## Model Context Protocol (MCP)
### Kurumsal Düzeyde MCP Sunucuları ve İstemcileri Geliştirme

---

## 1. Model Context Protocol (MCP) Nedir?

- Anthropic tarafından geliştirilen, yapay zeka modellerinin dış araçlar, bağlamlar ve kaynaklarla iletişimini standartlaştıran açık bir protokoldür.
- $N \times M$ entegrasyon karmaşasını çözer:
  - Her araç için ayrı SDK yazmak yerine, araçlar bir kere MCP sunucusu olarak sunulur.
  - Bütün uyumlu istemciler araçları otomatik keşfedip çağırabilir.

---

## 2. Mimari: Barındırıcı (Host), İstemci ve Sunucu

```
[Spring AI Uygulaması (MCP Host)]
              │
    ┌─────────┴─────────┐
    ▼                   ▼
[MCP İstemci A]    [MCP İstemci B]
 (STDIO ile)        (SSE / HTTP ile)
    │                   │
    ▼                   ▼
[Yerel Komut Satırı] [Kurumsal ERP MCP Sunucusu]
```

---

## 3. Spring Boot ile MCP Sunucusu Oluşturma

```java
public List<McpToolDescriptor> listTools() {
    return List.of(new McpToolDescriptor(
        "queryInventory",
        "Belirtilen stok kodu (SKU) için ambar stok miktarını döner",
        Map.of("type", "object", "properties", Map.of("sku", Map.of("type", "string")))
    ));
}
```

---

## 4. MCP Güvenliği ve Rol Tabanlı Yetkilendirme

- MCP sunucularını güvenilmeyen ortamlara yetkilendirmesiz açmayın.
- Çok katmanlı güvenlik kurun:
  - Rol tabanlı araç yetkilendirmesi (`ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST`).
  - Gelen argümanların şema doğrulaması.
  - Yapılan her araç çağrısının denetim (audit) loglarına kaydedilmesi.

---

## 5. Çıkarımlar ve İlkeler

1. MCP, yapay zeka araç ekosisteminin ortak iletişim standardı haline gelmektedir.
2. Spring Boot hem eski sistemleri MCP sunucusu olarak sunabilir hem de dış MCP sunucularına istemci olabilir.
3. Her MCP fonksiyonuna kesin yetki kontrolleri uygulayarak kurumsal güvenliği sağlayın.
