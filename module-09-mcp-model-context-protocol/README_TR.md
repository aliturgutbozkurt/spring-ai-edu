# Modül 09: Model Context Protocol (MCP)

Spring AI Eğitim Serisi **Modül 09**'a hoş geldiniz. Bu modülde Anthropic tarafından standartlaştırılan Model Context Protocol (MCP) protokolünü Spring Boot ile hem sunucu hem de istemci tarafında kurumsal düzeyde uygulamayı öğreneceksiniz.

## Ana Özellikler
- **MCP Sunucu Mimarisi**: Kurumsal ERP ve ambar sistemlerini standart MCP araç şemalarıyla dış dünyaya sunma.
- **MCP İstemci Ağ Geçidi**: Uzak araçları dinamik olarak keşfetme ve Spring AI modelleriyle güvenle çalıştırma.
- **Güvenlik Kum Havuzu (Sandboxing)**: Rol tabanlı yetkilendirme (`ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST`) ile araç erişim denetimi.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-09-mcp-model-context-protocol -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-09-mcp-model-context-protocol/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-09-mcp-model-context-protocol/homework/solution/pom.xml
```
