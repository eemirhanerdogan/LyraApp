# LyraApp Tipografi Sistemi

Bu doküman, LyraApp projesinin tipografi standartlarını ve Jetpack Compose Material 3 (M3) entegrasyon kurallarını tanımlar. Bu dosya, projedeki tipografi kararları için **Single Source of Truth (Tek Doğruluk Kaynağı)** olarak kabul edilir.

## 1. Temel Prensipler

*   **Font Ailesi:** Tipografi sistemi tamamen **Roboto** tabanlıdır.
*   **Font Yükleme:** Ek bir font dosyası (`.ttf` veya `.otf`) projeye dahil edilmeyecektir. Bunun yerine `FontFamily.Default` kullanılacaktır.
*   **Bağımlılıklar:** Tipografi sistemi için herhangi bir üçüncü taraf kütüphane veya yeni bir Gradle bağımlılığı eklenmeyecektir.
*   **Package:** Proje genelinde `com.example.lyraapp` paketi kullanılmaktadır.

## 2. Teknik Uygulama

Tipografi tanımlamaları daha sonra aşağıdaki dosyalarda organize edilecektir:

### LyraTypography Tanımlaması
Tüm `TextStyle` tanımlamaları `app/src/main/java/com/example/lyraapp/ui/theme/Type.kt` dosyası içerisinde `LyraTypography` adıyla bir `Typography` nesnesi olarak tanımlanacaktır.

```kotlin
// Örnek yapı (İleride uygulanacaktır)
val LyraTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    // Diğer M3 tanımlamaları...
)
```

### Temaya Entegrasyon
`app/src/main/java/com/example/lyraapp/ui/theme/Theme.kt` içindeki `MaterialTheme` çağrısında tipografi parametresi aşağıdaki gibi atanmalıdır:

```kotlin
MaterialTheme(
    colorScheme = colorScheme,
    typography = LyraTypography, // Buraya dikkat: LyraTypography kullanılacak
    content = content
)
```

## 3. Material Design 3 Tipografi Ölçekleri

Sistem, M3 standartlarındaki 15 temel ölçeği kullanır. Tüm ölçeklerde `FontFamily.Default` sabittir.

| Ölçek Adı | Font Weight | Size (sp) | Line Height (sp) | Letter Spacing (sp) |
| :--- | :--- | :--- | :--- | :--- |
| **Display Large** | Regular | 57 | 64 | -0.25 |
| **Display Medium** | Regular | 45 | 52 | 0 |
| **Display Small** | Regular | 36 | 44 | 0 |
| **Headline Large** | Regular | 32 | 40 | 0 |
| **Headline Medium** | Regular | 28 | 36 | 0 |
| **Headline Small** | Regular | 24 | 32 | 0 |
| **Title Large** | Regular | 22 | 28 | 0 |
| **Title Medium** | Medium | 16 | 24 | 0.15 |
| **Title Small** | Medium | 14 | 20 | 0.1 |
| **Body Large** | Regular | 16 | 24 | 0.5 |
| **Body Medium** | Regular | 14 | 20 | 0.25 |
| **Body Small** | Regular | 12 | 16 | 0.4 |
| **Label Large** | Medium | 14 | 20 | 0.1 |
| **Label Medium** | Medium | 12 | 16 | 0.5 |
| **Label Small** | Medium | 11 | 16 | 0.5 |

## 4. Kullanım Kuralları

1.  Yeni bir metin stili gerektiğinde, önce yukarıdaki standart ölçeklerden hangisine uygun olduğu kontrol edilmelidir.
2.  Doğrudan font büyüklüğü (hardcoded sp) vermek yerine `MaterialTheme.typography.bodyLarge` gibi temadan gelen stiller tercih edilmelidir.
3.  Özel durumlarda (renk değişimi vb.) mevcut stiller `copy` metodu ile türetilmelidir.

---
*Son Güncelleme: Ocak 2024*
