# 📞 Call Me Fluency

API em **Java + Spring Boot** para praticar inglês por "ligações" agendadas.

O usuário cadastra os horários livres da semana. Quando um deles chega, o sistema cria uma sessão de prática e, no futuro, "liga" para guiar uma conversa em inglês de acordo com o nível dele. Como não há telefonia real, a ligação será **simulada** por uma tela de chamada em um app ou página web.

> 🚧 **Em desenvolvimento.** O backend de contas, horários e sessões automáticas está pronto. Aviso ao usuário, conversa e tela de ligação ainda não existem.

## ✅ O que já funciona

- 🔐 Registro e login com JWT, senha com BCrypt e papéis (`USER` e `ADMIN`)
- 👑 Administrador inicial criado automaticamente
- 🗓️ CRUD dos horários livres (dia da semana + hora)
- ⏰ Criação automática de sessões na hora certa
- 🧹 Cancelamento automático das sessões não atendidas

## 🧰 Stack

Java 21 · Spring Boot 4.1 · PostgreSQL 17 · Flyway · Spring Security + JWT · MapStruct · Lombok

## 🚀 Como rodar

Requisitos: **Java 21** e **Docker**.

```bash
# 1. crie o .env (modelo abaixo)
# 2. suba o banco
docker compose up -d

# 3. rode a aplicação
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080` (porta padrão do Spring).

### 🔑 Arquivo `.env`

Nunca envie este arquivo para o git. Valores de exemplo:

```env
DB_NAME=callmefluency
DB_USER=postgres
DB_PASSWORD=troque-esta-senha
DB_HOST=localhost
DB_PORT=5432

JWT_EXPIRATION=3600000   # em milissegundos
JWT_KEY=troque-por-uma-chave-longa-e-aleatoria-com-32-caracteres-ou-mais

INITIAL_ADMIN_NAME=Admin
INITIAL_ADMIN_EMAIL=admin@example.com
INITIAL_ADMIN_PASSWORD=troque-esta-senha
```

## 🏗️ Arquitetura

Duas camadas, com uma regra: **o Core nunca depende da Infrastructure**.

```
src/main/java/.../call_me_fluency/
├── Core/              regras de negócio, sem Spring
│   ├── Domains/       User, PracticeSchedule, PracticeSession...
│   ├── UseCases/      uma classe por ação
│   ├── Gateway/       interfaces (banco, token, autenticação)
│   └── Managers/      PracticeSessionManager
└── Infrastructure/    tudo que fala com tecnologia
    ├── Controllers/   endpoints REST
    ├── Gateway/       implementa as interfaces do Core
    ├── Persistence/   entidades e repositórios JPA
    ├── Mappers/       Domain ↔ Entity ↔ DTO
    ├── Scheduling/    jobs agendados
    └── Config/        segurança, JWT, beans
```

⚠️ Os use cases **não têm `@Service`**. Ao criar um novo, registre o bean no `BeanConfiguration`.

## ⚙️ Como uma sessão nasce

Dois jobs rodam a cada minuto:

1. 🟢 **Criação:** para cada horário de hoje que cai nos próximos 5 minutos, cria uma sessão `SCHEDULED`.
2. 🔴 **Cancelamento:** cancela sessões `SCHEDULED` que passaram 5 minutos da hora marcada.

```
SCHEDULED → IN_PROGRESS → COMPLETED
    ↓
 CANCELED
```

## 🔌 Endpoints

Fora de `/v1/auth`, envie `Authorization: Bearer <token>`.

| Rota | Acesso |
|---|---|
| `POST /v1/auth/register` · `POST /v1/auth/login` | 🌐 Público |
| `DELETE /v1/users/me` · `PATCH /v1/users/me/password` | 👤 Logado |
| `/v1/practice-schedules` (POST, GET, PATCH, DELETE) | 👤 Logado |
| `GET /v1/practice-sessions/me` | 👤 Logado |
| `GET /v1/users` · `PATCH /v1/users/{id}/role` | 👑 Admin |
| `/v1/practice-schedules/users/{userId}` · `GET /v1/practice-sessions/user/{userId}` | 👑 Admin |

## 🗺️ Roadmap

- [x] Contas, login e permissões
- [x] Horários livres
- [x] Sessões automáticas
- [ ] Endpoints para iniciar, finalizar e cancelar sessão
- [ ] Fuso horário por usuário
- [ ] Aviso ao usuário (WebSocket / push)
- [ ] Conversa em inglês por nível
- [ ] Tela de ligação simulada
- [ ] Testes