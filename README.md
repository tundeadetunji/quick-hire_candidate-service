![CI](https://github.com/tundeadetunji/quick-hire_candidate-service/actions/workflows/ci.yml/badge.svg)

# 🧑‍💼 Candidate Service – QuickHire+ Microservices MVP

With **QuickHire+**, candidates can register and apply for jobs posted by recruiters.

📄 **API Docs:** [View Swagger UI](https://quick-hire-candidate-service.onrender.com/swagger-ui/index.html)

<br/>
<br/>

```  
+-------------------+       RabbitMQ        +-------------------+
|  Candidate Service|  ───────────────▶     |  Recruiter Service|
|                   |       🔔 Notify       |                   |
| - Apply to jobs   |◀───────────────       | - Manage Jobs     |
+-------------------+                      ◀| - Notify Admin    |
                                            +-------------------+
                                                   │
                                                   ▼
                                       +------------------------+
                                       |    Admin Service       |
                                       | - Logs notifications   |
                                       | - In-memory store      |
                                       +------------------------+
```

---

🧰 Tech Stack:
- ☕ Java 17
- 🌱 Spring Boot 3.x
- 📬 RabbitMQ (Messaging)
- 🛡️ Resilience4j (Observability)
- 🧪 JUnit 5, 🎭 Mockito (Testing)
- 🗄️ H2 / PostgreSQL (DB Layer)
- ⚙️ CI/CD via GitHub Actions

---

## 📬 Messaging

This service **publishes messages to RabbitMQ** when a candidate applies for a job.

- Routed via `app.exchange` with routing key `recruiter.notify`.
- Payload includes candidate and job post info.
- Consumed downstream by `recruiter-service` and forwarded to `admin-service`.

Steps to observe:

1. Apply to a job (`POST /apply` via Swagger).
2. Check logs in `recruiter-service`.
3. Confirm delivery via `GET /admin/messages`.

---

## 🧪 Testing

- Uses **JUnit 5**, **Mockito**, and Spring’s `@WebMvcTest`.
- CI powered by GitHub Actions.

### ✅ What’s Covered

- `POST /api/v1/candidate`
- `NotificationProducer` logic
- Persistence in `CandidateServiceImpl`

---

## ⚙️ Concurrency & Transactions

- All major workflows are **@Transactional**.
- Uses **Optimistic Locking** (`@Version`) on key entities to ensure consistency under concurrent access.

---

## 📊 Resilience4j Observability

Supports:

- ✅ Circuit Breakers  
- 🔁 Retry Policies  
- ⏱️ Rate Limiting  

Endpoints available via actuator:

/actuator/resilience4j.circuitbreakers
/actuator/resilience4j.retries
/actuator/resilience4j.ratelimiters


> ⚠️ Not publicly exposed on Render.

🧪 To test locally:
- Provide valid environment variables **or**
- Use H2 via `application-local.yml` / `.env.local`.

---

## 📘 Pagination

Not applicable in this service.

- `/viewApplications` returns full list (assumed low volume per candidate).

---

⚠️ Deployment Note on Cold Start Behavior (Render)

QuickHire+ is deployed on [Render](https://render.com).

When inactive for some time, services may enter a **"cold" state**, resulting in:

- Initial request latency (10–30s) as services spin up
- Temporary 502/504 responses until startup completes

🧪 To trigger warm-up manually:
- Open Swagger UI or hit a simple `/health` or `/actuator/info` endpoint
- Wait a few seconds for services to fully boot
