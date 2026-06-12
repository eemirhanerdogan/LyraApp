# MVI Sözleşmeleri (Contracts)

MVI mimarisinde her ekran, kendi veri akışını yöneten bir sözleşme (Contract) yapısına sahip olmalıdır. Bu yapı `State`, `Intent` ve `Effect` bileşenlerinden oluşur.

## 1. State (Durum)
Ekranın o anki görsel durumunu temsil eder. Genellikle bir `data class` olarak tanımlanır.

*   **Kural:** Immutable (değiştirilemez) olmalıdır.
*   **Kapsam:** Loading durumları, liste verileri, hata mesajları vb. her şey burada yer alır.
*   **Örnek:** `RegisterState`, `LoginState`.

## 2. Intent (Eylem/Niyet)
Kullanıcının veya sistemin gerçekleştirmek istediği eylemleri temsil eder. Genellikle bir `sealed interface` veya `sealed class` olarak tanımlanır.

*   **Kural:** UI katmanından ViewModel'e gönderilen tek eylem türüdür.
*   **Örnek:** `OnLoginClicked`, `OnEmailChanged`.

## 3. Effect (Yan Etki)
Ekranın durumunu kalıcı olarak değiştirmeyen, genellikle bir kez gerçekleşen olayları temsil eder.

*   **Kural:** Navigasyon, Toast mesajı gösterme veya Dialog açma gibi durumlar için kullanılır.
*   **Akış:** `Channel` veya `SharedFlow` üzerinden iletilir.
*   **Örnek:** `NavigateToHome`, `ShowErrorMessage`.

## Sözleşme Organizasyonu
Sözleşmeler `com.example.lyraapp` paket yapısı altında, ilgili ekranın klasöründe (örneğin `ui.screen.login`) bir `Contract` dosyası içinde toplanmalıdır.
