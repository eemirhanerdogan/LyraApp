# ViewModel Kuralları ve Sorumlulukları

MVI mimarisinde ViewModel, UI ile Business Logic arasındaki köprüdür. LyraApp projesinde ViewModel katmanı için aşağıdaki kurallar zorunludur.

## 1. Temel Sorumluluklar
*   **Intent Karşılama:** UI'dan gelen tüm `Intent`'leri karşılar ve ilgili işlemleri başlatır.
*   **State Yönetimi:** Mevcut ekran durumunu (State) saklar ve günceller.
*   **Effect Tetikleme:** Navigasyon veya hata mesajı gibi tek seferlik olayları (Effect) yayınlar.
*   **Business Logic:** İş mantığını kendisi icra etmez, Repository katmanını çağırarak yönetir.

## 2. Teknik Uygulama Kuralları

### State Yayınlama
*   State, dış dünyaya bir `StateFlow` olarak sunulmalıdır.
*   ViewModel içinde `MutableStateFlow` olarak tutulmalı, dışarıya `asStateFlow()` ile salt-okunur olarak açılmalıdır.
*   **Paket:** `com.example.lyraapp` altındaki ilgili ekran paketinde yer almalıdır.

### Intent İşleme
*   UI katmanından gelen niyetleri yakalamak için tek bir `onIntent(intent: Contract.Intent)` fonksiyonu bulunmalıdır.
*   Bu fonksiyon içinde `when` yapısı kullanılarak her intent tipi için ilgili business logic metoduna yönlendirme yapılmalıdır.

### Effect Yayınlama
*   Yan etkiler (Effect) için `Channel` veya `SharedFlow` kullanılmalıdır.
*   UI katmanı bu efektleri `LaunchedEffect` içinde dinlemelidir.

## 3. Veri Katmanı Etkileşimi (Repository)
*   ViewModel asla doğrudan bir "Implementation" sınıfına bağımlı olmamalıdır.
*   Her zaman bir `interface` üzerinden Repository ile konuşmalıdır.
*   Backend servisleri tamamlanana kadar **Fake Repository** sınıfları kullanılmalıdır.
*   Gerçek API geldiğinde ViewModel kodu değiştirilmeden sadece Repository implementasyonu güncellenmelidir.

## 4. UI Katmanı Kısıtlamaları
*   UI (Composable fonksiyonlar) asla business logic barındıramaz.
*   UI katmanı sadece `State`'i "render" eder ve kullanıcı etkileşimlerini `Intent` olarak ViewModel'e "emit" eder.
*   Veri dönüştürme (formatting) gibi işlemler mümkünse ViewModel veya UseCase seviyesinde yapılmalıdır.
