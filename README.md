# API Automation Project — Swagger Petstore (`/store` endpoints)

## 🔧 Інструкції з налаштування та запуску проекту

### ⚙️ Вимоги
- Java 11+
- Maven 3.5+
- IDE (наприклад, IntelliJ IDEA)
- Браузер для перегляду Allure-звітів

### 🚀 Як запустити тести

1. **Клонувати репозиторій**
   ```bash
   git clone https://github.com/your-username/petstore-api-tests.git
   cd petstore-api-tests
   
2. **Запустити тести з Maven**
    ```bash
   mvn clean test

3. Згенерувати та відкрити звіт Allure
   ```bash
   mvn allure:serve

## Мій підхід до тестування

1. REST Assured використовується як основна бібліотека для взаємодії з API
2. TestNG використовується як фреймворк для організації тестів
3. Тести запускаються паралельно, що дозволяє значно зекономити час виконання. Для цього налаштований **testng.xml** файл
4. Реалізовані залежності між тестами. Наприклад:
   - deletePurchaseOrderByID виконується лише після успішного завершення findPurchaseOrderByID
   - findPurchaseOrderByID, своєю чергою, залежить від успішного виконання placeOrderForPet
5. **Allure** інтегровано для генерації інформативних HTML-звітів з прикладами запитів, відповідей усіх **requests** та **response**
6. Конфігурації (базовий URL, заголовки, фільтри) винесені у окремий **RequestSpecification**, що спрощує підтримку та розширення.
7. Тести поділені на позитивні та негативні для зручної організації та аналізу

