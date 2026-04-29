# 🚀 Mini-CRM (B2B) – Fullstack Projekt

Ein modernes **Mini-CRM-System für B2B/Großhandel**, entwickelt mit:

- ⚛️ React + TypeScript (Frontend)
- ☕ Spring Boot (Backend)
- 🐘 PostgreSQL (Datenbank)
- 🐳 Docker & Docker Compose (Infrastruktur)

Ziel dieses Projekts ist es, ein **skalierbares Fullstack-System** aufzubauen, das reale Geschäftsprozesse im Vertrieb abbildet – von Kundenverwaltung bis Angebotsverfolgung.

---

# 🎯 Projektziel

Dieses CRM richtet sich primär an **Großhändler / B2B-Unternehmen** und fokussiert sich auf:

- Firmen statt Einzelkunden
- Vertriebsprozesse (Angebote, Follow-Ups)
- Kundenhistorie & Beziehungen
- Aufgaben- und Aktivitätsmanagement

---

# 🧱 Projektstruktur

```
mini-crm/
│
├── backend/        # Spring Boot API
├── frontend/       # React + Vite Frontend
├── docker-compose.yml
├── .env
└── README.md
```

---

# 🛠️ Tech Stack

## Backend

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Frontend

- React (Vite)
- TypeScript
- TailwindCSS

## Infrastruktur

- Docker
- Docker Compose

---

# 🧠 Datenbank-Konzept (B2B-CRM)

## 🏢 Company

- Name
- Status
- Umsatz
- Standardrabatt

## 👤 ContactPerson

- Vorname, Nachname
- E-Mail
- Position
- Beziehung: Many-to-One zu Company

## 📝 Interaction

- Text
- Datum
- Typ (Notiz, Besuchsbericht, Anruf)
- Beziehung: Many-to-One zu Company

## 📌 Task

- Titel
- Fälligkeitsdatum
- Priorität
- Status
- Beziehung: Many-to-One zu Company

## 💰 Offer

- Angebotsnummer
- Wert
- Inhalt
- Status
- Beziehung: Many-to-One zu Company

---

# 🖥️ Frontend Features (geplant)

## 🔐 Login

- Benutzerverwaltung (Innen-/Außendienst)

## 📊 Dashboard

- Gesamtumsatz
- Anzahl Kunden
- Offene Aufgaben
- Aktive Angebote
- Priorisierte Tasks

## 👥 Kundendetailseite

- Stammdaten
- Ansprechpartner
- Angebote
- Notizen

## 📌 Task Detailseite

- Status ändern
- Details anzeigen

---

# ▶️ Projekt starten

```bash
docker-compose up --build
```

Danach erreichbar unter:

- Frontend: http://localhost:5173
- Backend: http://localhost:8080

---

# 🔐 Environment Variablen

Erstelle eine `.env` Datei basierend auf:

```
.env.example
```

---

# 💬 Motivation

Dieses Projekt dient als Lernprojekt.

---
