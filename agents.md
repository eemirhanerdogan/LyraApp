# Agent Çalışma Prensipleri

Bu dosya, LyraApp projesinde çalışan yapay zeka asistanlarının uyması gereken temel kuralları tanımlar.

## Kurallar

1.  **Dosya Limiti:** Tek seferde en fazla 5 dosya üzerinde işlem yapılmalıdır.
2.  **Planlama:** Herhangi bir kod yazımına veya dosya değişikliğine başlamadan önce kısa bir plan sunulmalıdır.
3.  **Onay Mekanizması:** Plan kullanıcı tarafından onaylanmadan implementasyona geçilmemelidir.
4.  **Bilgi Doğruluğu:** Eksik veya belirsiz bilgi durumunda uydurma çözüm üretilmemeli, kullanıcıya soru sorulmalıdır.
5.  **İşlem Sonu Raporu:** Her işlem sonunda:
    *   Değişen/oluşturulan dosyaların listesi sunulmalıdır.
    *   Yeni dependency eklenip eklenmediği belirtilmelidir.
    *   Derleme durumu (happy-path test) hakkında bilgi verilmelidir.
6.  **Referans Kaynakları:**
    *   Mimari kararlar için `docs/decisions.md` dosyası referans alınmalıdır.
    *   MVI implementasyon kuralları için `docs/architecture` altındaki dokümanlar referans alınmalıdır.
7.  **Paket Yapısı:** Proje genelinde paket adı olarak `com.example.lyraapp` kullanılmalıdır.
