# **Identity Verification API**

Spring Boot REST API za upravljanje korisnicima i KYC (Know Your Customer) procesom, uključujući autentifikaciju i autorizaciju putem JWT tokena.

## **a) Opis projekta**

Ovaj projekt demonstrira backend sustav za:

- Upravljanje korisnicima (CRUD)
- KYC proces (pokretanje, status, update)
- JWT autentifikaciju i autorizaciju
- Role-based pristup (USER / ADMIN)
- Globalni error handling i proper HTTP response codes

Projekt je implementiran u Spring Boot-u, koristi PostgreSQL i Spring Security.

## **b) Tehnologije**

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- PostgreSQL  
- Spring Security + JWT  
- Lombok  
- Maven  

## **c) Baza podataka**

- **Database:** PostgreSQL  
- **Schema:** `kyc`  
- **Tablice:**
  - `customers` – id, first_name, last_name, email, password_hash, phone, date_of_birth, country, role, created_at  
  - `kyc_request` – id, customer_id, status (PENDING, VERIFIED, REJECTED), external_reference_id, created_at, updated_at  

- **Napomena:** id-evi resetirani na 1 za čisto testiranje

## **d) Endpointovi i funkcionalnosti**

### **1. Customer CRUD**

| Method | Endpoint          | Body / Params                        | Description          |
|--------|-----------------|--------------------------------------|--------------------|
| POST   | `/api/customers` | `CustomerCreateRequest` JSON         | Kreira novog korisnika |
| GET    | `/api/customers/{id}` | PathVariable `id`                  | Dohvati korisnika po ID-u |

### **2. KYC**

| Method | Endpoint                     | Body / Params                          | Description             |
|--------|------------------------------|----------------------------------------|------------------------|
| POST   | `/api/kyc/{customerId}`       | PathVariable `customerId`               | Pokreni KYC za korisnika |
| GET    | `/api/kyc/{customerId}`       | PathVariable `customerId`               | Dohvati status KYC-a     |
| PATCH  | `/api/kyc/{customerId}/status`| `KycStatusUpdateRequest` JSON           | Ažuriraj status KYC-a    |

### **3. Autentifikacija**

| Method | Endpoint             | Body                                   | Description |
|--------|--------------------|----------------------------------------|------------|
| POST   | `/api/auth/register` | `CustomerCreateRequest` JSON          | Registracija korisnika |
| POST   | `/api/auth/login`    | `AuthRequest` JSON                    | Prijava korisnika, vraća JWT token |

**JWT token se šalje u headeru:** Authorization: Bearer <token>

- Zaštićeni endpointi zahtijevaju token  
- Role-based pristup: USER / ADMIN  

## **e) DTO klase**

- `CustomerCreateRequest` – kreiranje korisnika  
- `CustomerResponse` – response korisnika  
- `KycResponse` – response KYC requesta  
- `KycStatusUpdateRequest` – patch statusa KYC-a  
- `AuthRequest` / `AuthResponse` – login i token  

## **f) Mapper klase**

- `CustomerMapper` – mapira između entity i DTO  
- `KycRequestMapper` – mapira između entity i KycResponse DTO  

## **g) Service layer**

- `CustomerService` – CRUD operacije i enkripcija lozinke  
- `KycService` – start, get i update KYC  
- `CustomUserDetailsService` – učitava korisnika za Spring Security  

## **h) Global Exception Handling**

- `@RestControllerAdvice` sa handlerima za:
  - `NotFoundException` → 404  
  - `BadRequestException` → 400  
  - Ostale runtime → 500  

- Testiranje:
  - GET `/api/customers/9999` → 404 Customer not found  
  - PATCH `/api/kyc/1/status` bez body → 400 Bad Request  

## **i) Primjeri JSON requesta**

### **Kreiranje korisnika**

```json
{
  "firstName": "Joe",
  "lastName": "Doe",
  "email": "john.doe@test.com",
  "password": "securepassword123!",
  "phone": "+385912345678",
  "dateOfBirth": "1990-01-01",
  "country": "Croatia"
}

---

Login
{
  "email": "joe.doe@test.com",
  "password": "securepassword123!"
}

---

Response

{
  "token": "<JWT_TOKEN>"
}

---

Start KYC

POST /api/kyc/1
Authorization: Bearer <JWT_TOKEN>

{
  "id": 1,
  "status": "PENDING",
  "createdAt": "2025-12-26T13:00:00Z",
  "updatedAt": "2025-12-26T13:00:00Z",
  "externalReferenceId": "uuid-string"
}

---

Update KYC status

PATCH /api/kyc/1/status
Authorization: Bearer <JWT_TOKEN>

{
  "status": "VERIFIED"
}

---

POKRETANJE PROJEKTA

Clone repo: git clone https://github.com/antoniomuzar/identity-verification-api.git

Konfiguriraj application.properties za svoju bazu

Pokreni:

mvn clean install
mvn spring-boot:run

---

TEST

Testna dokumentacija dostupna u projektu: docs/postman
