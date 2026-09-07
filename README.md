# 01 - Java + Maven (Spring Boot) REST API

A tiny Spring Boot REST API you can build, run, and ship. It exists for one reason: **for you to deploy it** as DevOps practice. The app is the easy part - the DevOps journey is the point.

---

## The 7 DevOps Questions

These are the only things you need to know to ship *any* app. Here are the answers for this one:

| # | Question | Answer for this app |
|---|----------|---------------------|
| 1 | **Language / runtime?** | Java 17 (JRE to run, JDK to build) - base image `eclipse-temurin:17` |
| 2 | **How do you build it?** | `mvn package` (uses Maven + `spring-boot-maven-plugin`) |
| 3 | **What's the artifact?** | A single runnable "fat" JAR: `target/demo.jar` |
| 4 | **Start command?** | `java -jar target/demo.jar` |
| 5 | **Which port?** | `8080` by default (override with the `SERVER_PORT` env var) |
| 6 | **Config / secrets?** | Environment variables (e.g. `SERVER_PORT`) - nothing config-worthy is hardcoded |
| 7 | **Health check URL?** | `GET /health` -> `200 OK` with `{"status":"ok"}` |

---

## Run it locally

You need **JDK 17+** and **Maven** installed (`java -version`, `mvn -version`).

**Option A - run straight from source (fastest for dev):**

```bash
mvn spring-boot:run
```

**Option B - build the artifact, then run it (this is what your Dockerfile will do):**

```bash
mvn package
java -jar target/demo.jar
```

Want a different port? Set the env var (12-factor, no code change):

```bash
SERVER_PORT=9090 java -jar target/demo.jar
```

### Test all 3 endpoints

With the app running, open another terminal:

```bash
curl http://localhost:8080/
curl http://localhost:8080/health
curl http://localhost:8080/api/items
```

What you should see:

```text
# GET /
{"message":"Welcome! This is a Java + Maven (Spring Boot) DevOps practice app.","stack":"Java 17 / Spring Boot / Maven","endpoints":"/  /health  /api/items"}

# GET /health
{"status":"ok"}

# GET /api/items
[{"id":1,"name":"Dockerize me"},{"id":2,"name":"Add a CI/CD pipeline"},{"id":3,"name":"Deploy to Kubernetes"},{"id":4,"name":"Go live on AWS"}]
```

(Key order in the JSON may vary - that's fine.)

---

## Your Practice Mission

The app works. Now **you** bring the DevOps. None of the files below are included on purpose - building them *is* the exercise. Stuck? Open the matching TaskBoard module and adapt its files; the pattern transfers directly.

- [ ] **Dockerize it** - write a `Dockerfile`, build an image, run a container (**M14**)
- [ ] **Compose** - wire it up in `docker-compose.yml` (**M15**)
- [ ] **CI/CD** - a `Jenkinsfile`: build -> test -> image -> deploy (**M17**)
- [ ] **Vagrant VM** - run it on a local VM (**M13**)
- [ ] **Ansible** - provision the server / install the JRE with a playbook (**M16**)
- [ ] **Kubernetes** - `Deployment` + `Service` + `Ingress`, then scale it (**M18 / M19**)
- [ ] **AWS + Terraform** - go live on AWS, built with Terraform (**M20 / M21**)

> Tip for Docker: this is a JVM app, so a **multi-stage build** (build with the JDK image, run on a slim JRE image) keeps your final image small. Remember to `EXPOSE 8080`, pass `SERVER_PORT` as config, and use `/health` for your `HEALTHCHECK` and K8s probes.
