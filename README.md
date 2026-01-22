## Задание: Разработка упрощенной CRM-системы
## Описание задачи:
Вы должны разработать CRM-систему, которая будет управлять информацией о продавцах и их
транзакциях. Система должна включать возможности для создания, чтения, обновления и
удаления данных о продавцах и транзакциях. Система также должна включать функции аналитики
для обработки и анализа данных. 

## API

### Seller
| Method | Url                                    | Описание                                            |
|--------|----------------------------------------|:----------------------------------------------------|
| GET    | /seller/get/{id}                       | Список всех продавцов                               | 
| GET    | /seller/getAll                         | Инфо о конкретном продавце                          |
| POST   | /seller/create                         | Создать нового продавца                             |
| PATCH  | /seller/update/{id}                    | Обновить инфо о продавце                            |
| DELETE | /seller/delete/{id}                    | Удалить продавца и все его транзакции               |                              
| GET    | /seller/findTopSellers                 | Получить самых продуктивных продавцов               | 
| GET    | /seller/findSellersWithSumLess/{sum}   | Получить список продавцов с суммой меньше указанной | 

### Transaction

| Method | Url                                    | Описание                                    |
|--------|----------------------------------------|:--------------------------------------------|
| GET    | /transaction/allTransaction            | Получить список всех транзакций             | 
| GET    | /transaction/get/{id}                  | Получить информацию о конкретной транзакции |
| POST   | /transaction/create                    | Создать новую транзакцию                    | 
| GET    | /transaction/getBySellerId/{id}        | Получить все транзакции продавца            |

## Зависимости
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

## Примеры использования API  
Запрос:
````
curl -X GET "http://localhost:8080/seller/get/1" -H "Accept: application/json"
````
Ответ: 
````
{
    "id": 1,
    "name": "Иван Иванов",
    "contactInfo": "+79991234567",
    "registrationDate": "2026-01-22T12:34:56"
}
````

Запрос:
````
curl -X POST "http://localhost:8080/seller/create" \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Сергей Сергеев",
    "contactInfo": "+79990001122"
}'
````
Ответ:
````
{
    "id": 3,
    "name": "Сергей Сергеев",
    "contactInfo": "+79990001122",
    "registrationDate": "2026-01-22T14:00:00"
}
````

## Инструкция по сборке

### Требования

1) Установленная JDK (версия 17)
2) Установленный PostgreSql
3) Установленный Gradle
4) Созданная база данных test_db (Пользователя, пароль и имя базы данных можно поменять в application.yaml)

### Запуск
```
./gradle wrapper
./gradlew bootRun
```
