![CI](https://github.com/tundeadetunji/quick-hire_candidate-service/actions/workflows/ci.yml/badge.svg)

## 📬 Messaging and Observability
## 🧪 Testing
## ⚙️ Concurrency & Transactions
## 📘 Pagination


## 📬 Messaging and Observability

This service **publishes messages to RabbitMQ** when a candidate applies for a job.

- The message is routed to the `recruiter.notify` queue via `app.exchange`.
- The payload includes details like candidate name and job post applied for.
- The `admin-service` listens and logs all messages for monitoring or testing.

To observe messaging:
- Use Swagger to trigger a job application (via `/apply`).
- Then call `/admin/messages` to verify the message was received.

## 🧪 Testing

This service uses **JUnit 5**, **Mockito**, and Spring Boot’s testing support (`@WebMvcTest`) for controller-level tests.

Tests run automatically via GitHub Actions on push or pull request to `master`.

### ✅ What’s Covered

- Registering a candidate via `POST /api/v1/candidate`
- Messaging integration via RabbitMQ (`NotificationProducer`)
- Service-layer persistence logic (`CandidateServiceImpl`)

## ⚙️ Concurrency & Transactions

This service uses Spring’s `@Transactional` to ensure safe, atomic operations during key candidate workflows:

- Registering or updating accounts
- Deleting accounts
- Applying to job posts

It also employs **optimistic locking** via `@Version` fields on entities like `Candidate` and `CandidateApplication`.

This ensures that if two concurrent operations try to modify the same candidate (e.g., apply to the same job), one will be rejected — preserving data integrity without needing pessimistic locks.

## 📘 Pagination

This service does not expose large paginated lists — most endpoints return single records or short collections.

The only collection endpoint, `viewApplications(Long candidateId)`, currently returns the full list of a candidate's applications. This decision was made intentionally, assuming low volume per candidate in this MVP.

If needed, it could be optimized with a custom paginated query in the future. For now, it prioritizes simplicity without compromising performance.
