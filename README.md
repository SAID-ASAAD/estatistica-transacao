# Transaction Statistics API

This project is a REST API, developed in Java with Spring Boot, which calculates statistics on financial transactions received within a standard (and adjustable) period of 60 seconds.

The bilingualism present in the code and JSON fields (using Portuguese terms like `valor` and `dataHora`) is intentional, as the project was proposed as a technical challenge by a Brazilian company with these terms as requirements.

P.S.: The language alternation in the commits messages is a mistake, sorry for that.

### Tech Stack

*   **Java 21**: The core programming language.
*   **Spring Boot 3**: The framework for creating the REST API.
*   **Maven**: The build and dependency management tool.
*   **Docker**: Used for containerizing the application.
*   **Spring Boot Actuator**: Provides observability features like health checks and metrics.
*   **SpringDoc OpenAPI**: For generating API documentation and the Swagger UI.
*   **JUnit5 and Mockito**: For unity tests.

### Prerequisites

*   Java JDK 21 (or JRE 21)
*   Maven 3.9+
*   Git
*   Docker (Optional)

## How to Run

### 1. Local Execution

1.  Clone the project repository:
    ```bash
    git clone https://github.com/SAID-ASAAD/estatistica-transacao.git
    ```

2.  Compile and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
    
### 2. Running with Docker (Optional)

1.  **Build the Docker image:**
    Make sure Docker is installed and running, then execute the following command in the project root:
    ```bash
    docker build -t estatistica-transacao .
    ```

2.  **Run the container:**
    ```bash
    docker run -p 8080:8080 estatistica-transacao
    ```
    
## API Documentation

The URL for the Swagger UI is `http://localhost:8080/swagger-ui/index.html#/`.

---

### 1. Add a Transaction

Adds a new transaction to be considered in the statistics.

*   **Endpoint:** `POST /transacao`
*   **Request Body:** `application/json`

**Parameters:**

| Parameter  | Type           | Description                               |
| :--------- | :------------- | :---------------------------------------- |
| `valor`    | `BigDecimal`   | **Required**. The transaction amount.     |
| `dataHora` | `OffsetDateTime` | **Required**. The transaction timestamp (ISO 8601 format). |

**Example Request:**

```json
{
  "valor": "12.34",
  "dataHora": "2024-08-01T10:00:00.000Z"
}
```

**Success Response:**

*   **Code:** `201 Created`
*   **Content:** Empty

---

### 2. Get Statistics

Calculates and returns the statistics for transactions that occurred within the specified time interval, ending at the current moment.

*   **Endpoint:** `GET /estatistica`

**Query Parameters:**

| Parameter      | Type      | Description                                                                                             |
| :------------- | :-------- | :------------------------------------------------------------------------------------------------------ |
| `intervaloBusca` | `integer` | **Optional**. The time window in seconds to look back for transactions. **Defaults to 60 seconds.** |

**Example Request:**

```http
GET /estatistica?intervaloBusca=30
```

**Success Response:**

*   **Code:** `200 OK`
*   **Content:** `json body`

**Example Response Body:**

```json
{
  "count": 2,
  "sum": "150.50",
  "avg": "75.25",
  "min": "50.50",
  "max": "100.00"
}
```

---

### 3. Clear Transactions

Deletes all previously recorded transactions.

*   **Endpoint:** `DELETE /transacao`

**Success Response:**

*   **Code:** `204 No Content`
*   **Content:** Empty
