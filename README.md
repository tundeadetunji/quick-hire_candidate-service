![CI](https://github.com/tundeadetunji/quick-hire_candidate-service/actions/workflows/ci.yml/badge.svg)

# Candidate Service of QuickHire+ microservices MVP
With QuickHire+, Recruiters can create jobs, manage job posts, candidate applications.
<br />
View the docs <a href="https://quick-hire-candidate-service.onrender.com/swagger-ui/index.html">here</a>.

<br />
<br />

In this readme:

#### 📬 Messaging
#### 🧪 Testing
#### ⚙️ Concurrency & Transactions
#### 📊 Resilience4j Observability
#### 📘 Pagination

<br />
<br />
<br />

## 📬 Messaging

This service **publishes messages to RabbitMQ** when a candidate applies for a job.

- The message is routed through `app.exchange` using the `recruiter.notify` routing key.
- The payload includes details like candidate name and job post applied for.
- The `recruiter-service` listens for this message, logs it, and forwards a copy to `admin.notify`.

To observe messaging:
1. Use Swagger to apply to a job (`POST /apply`).
2. Check logs in `recruiter-service` for receipt.
3. Call `/admin/messages` to verify the final message delivery.

<br />
<br />
<br />

## 🧪 Testing

This service uses **JUnit 5**, **Mockito**, and Spring Boot’s testing support (`@WebMvcTest`) for controller-level tests.

Tests run automatically via GitHub Actions on push or pull request to `master`.

### ✅ What’s Covered

- Registering a candidate via `POST /api/v1/candidate`
- Messaging integration via RabbitMQ (`NotificationProducer`)
- Service-layer persistence logic (`CandidateServiceImpl`)

<br />
<br />
<br />

## ⚙️ Concurrency & Transactions

This service uses Spring’s `@Transactional` to ensure safe, atomic operations during key candidate workflows:

- Registering or updating accounts
- Deleting accounts
- Applying to job posts

It also employs **optimistic locking** via `@Version` fields on entities like `Candidate` and `CandidateApplication`.

This ensures that if two concurrent operations try to modify the same candidate (e.g., apply to the same job), one will be rejected — preserving data integrity without needing pessimistic locks.

<br />
<br />
<br />

## 📊 Resilience4j Observability

This service uses **Resilience4j** to handle transient failures with:

- Circuit Breakers
- Retry Policies
- Rate Limiting

You can observe real-time Resilience4j metrics using the built-in Spring Boot Actuator endpoints:

- `/actuator/resilience4j.circuitbreakers`
- `/actuator/resilience4j.retries`
- `/actuator/resilience4j.ratelimiters`

> ⚠️ These endpoints are **internal** and not exposed publicly on Render.

### 🧪 Local Testing

If testing locally, ensure:

- You provide valid environment variables **or**
- You temporarily switch to an in-memory H2 database (e.g. via `application-local.yml` or `.env.local`)

This allows the app to boot and actuator endpoints to respond.

<br />
<br />
<br />

## 📘 Pagination

This service does not expose large paginated lists — most endpoints return single records or short collections.

The only collection endpoint, `viewApplications(Long candidateId)`, currently returns the full list of a candidate's applications. This decision was made intentionally, assuming low volume per candidate in this MVP.

If needed, it could be optimized with a custom paginated query in the future. For now, it prioritizes simplicity without compromising performance.
