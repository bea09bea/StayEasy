# StayEasy - Accommodation booking REST API
Utvecklade ett airbnb inspirerat boknings API med Java och Spring Boot. Implementerade CRUD-operationer, relationsbaserad datamodellering, validering och felhantering samt bokningslogik. Använde PostgreSQL för datalagring och Swagger för dokumentation och testning.

# Filstruktur
```
stayeasy/
│
├── StayEasyApplication.java
│
├── config/
│   └── WebConfig.java
│
├── controller/
│   ├── UserController.java
│   └── PropertyController.java
│
├── service/
│   ├── UserService.java
│   └── PropertyService.java
│
├── repository/
│   ├── UserRepository.java
│   └── PropertyRepository.java
│
├── model/
│   ├── User.java
│   └── Property.java
│
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── DuplicateResourceException.java
│
└── assembler/
   ├── UserModelAssembler.java
   └── PropertyModelAssembler.java
```
# Mapp struktur
Uppdelat i 7 mappar:
* config: CORS konfiguration som tillåter frontend att kommunicera med backend
* controller: tar emot HTTP-request och returnerar HTTP svar
* service: hanterar kopplingen mellan användare och boenden
* repository: kommunicerar med databas via JPA
* model: innehåller klasser för User och Property
* exception: hanterar fel och returnerar lämpliga statuskoder och felmeddelanden
* assembler: omvandlar objekt till API svar med HATEOAS länkar


# Regler
* Radering av användare: En användare kan inte raderas om den är kopplad till ett boende, antingen som ägare (host) eller som hyresgäst.
* Radering av boende: Ett boende kan endast raderas om ingen användare hyr det
* Skapande av boende: Ett boende kan endast skapas om angivet hostId tillhör en användare som redan finns i databasen
* Email och telefonnummer: måste följa regex mönster och vara unika för varje användare
* Pris per natt: pricePerNight kan anges med 2 decimaler exempelvis 10.00
* Host id: hostId måste vara ett positivt tal
* Max antal gäster: maxGuests måste vara minst 1

# HATEOAS
Både User och Property använder EnityModel för enskilda objekt och CollectionModel för samling av objekt.

# Swagger UI
Swagger UI är tillgängligt när applikationen körs. Används för att dokumentera och testa API:ets endpoints.

Länk: http://localhost:5500/swagger-ui/index.html
