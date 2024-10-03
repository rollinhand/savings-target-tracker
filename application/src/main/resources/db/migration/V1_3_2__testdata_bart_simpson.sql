-- Benutzer 3: Bart Simpson (Ziel noch nicht erreicht)
INSERT INTO stt_savings_targets (id, monthly_savings, name, target_amount, target_date, user_id)
VALUES ('a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f', 20, 'Skateboard', 300, '2024-12-31', '58d3f1c9-8796-4f72-a457-8280d2eb3f1b');
-- Transaktionen (noch nicht erreicht)
INSERT INTO stt_transactions (id, amount, date, savings_target_id)
VALUES ('eb8b033c-584b-49fc-834f-60dc520e3e73', 20, '2024-01-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f'),
       ('1ba1be3f-83dd-4d53-9f0d-23a37969fef6', 20, '2024-02-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f'),
       ('8d535ea4-7bad-46fc-9c28-93ed1a3224e6', 20, '2024-03-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f'),
       ('f1bece2e-39e6-4066-9323-017138576a22', 20, '2024-04-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f'),
       ('120cd092-44a5-497b-8d08-7d8896acdbe2', 20, '2024-05-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f'),
       ('f51d4bdb-6689-4038-a90c-0ad393dfe1d0', 20, '2024-06-01', 'a345f3c8-7e5f-4d7e-9b6f-3f7d2e8b7e9f');