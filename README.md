# Sparziel-Tracker (Savings Target Tracker)

Eine Anwendung, die Benutzern hilft, ihre Sparziele zu verfolgen. 
Benutzer können ihre Sparziele festlegen, regelmäßige Beiträge hinzufügen und den Fortschritt verfolgen. 
Die Anwendung kann Benutzer auch benachrichtigen, wenn sie ihre Sparziele erreichen oder wenn zusätzliche Beiträge 
erforderlich sind.

## Bauen

Die gesamte Anwendung kann als Bundle gebaut werden und dann lokal ausgeführt werden:
`mvn clean verify meecrowave:bunlde`

Wenn der Server über die Hauptklasse `SavingsTargetTrackerApp` direkt gestartet wird, dann ist darauf zu achten,
dass sowhl die log4j2.xml und das Verzeichnis für die Flyway-Migrationen mit im Classpath sind.
Ansonsten werden die Migrationen nicht gefunden und ausgeführt.

## Module application

Die Gesamtanwendung mit der Konfiguration von Meecrowave und der Anbindung an eine H2-Datenbank.
Zusätzlich ist Flyway integriert, um ein initiales Testdatenset anzulegen.

## Module user

Das Modul zur Verwaltung der Benutzer der Anwendung.
Die Entität `User` ist auf die Tabelle `stt_users` gemappt, da die H2-Datenbank bereits eine Tabelle `users` besitzt.
Somit können automatische Integrationstests gegen die H2-Datenbank ausgeführt werden.
Hierfür wird zum Testen eine Meecrowave-Instanz gestartet.

## Module savings

Das Modul zur Verwaltung der Sparziele und zum Dokumentieren der Ein- und Auszahlungen.
Für die Tabellen wird weiterhin das Präfix `stt` verwendet, um die Tabellen eindeutig zu identifizieren.

## Testdaten
Über Flyway werden zusätzliche zu 5 von den 20 Anwendern im System exemplarische Testdaten erzeugt:

* __Homer Simpson__ erreicht sein Sparziel durch monatliche Einzahlungen ohne Sonderzahlungen.
* __Marge Simpson__ erreicht ihr Ziel durch regelmäßige Einzahlungen und zwei größere Sonderzahlungen.
* __Bart Simpson__ hat sein Sparziel noch nicht erreicht, da er nur kleinere regelmäßige Einzahlungen getätigt hat.
* __Lisa Simpson__ übererreicht ihr Ziel durch eine Kombination aus regelmäßigen Einzahlungen und Sonderzahlungen.
* __Maggie Simpson__ erreicht ihr Sparziel durch kleine, aber kontinuierliche Einzahlungen.