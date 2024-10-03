-- Benutzer 5: Maggie Simpson (Ziel erreicht)
INSERT INTO stt_savings_targets (id, monthly_savings, name, target_amount, target_date, user_id)
VALUES ('622cf28d-18cb-4cc7-ad77-ea86aad55a11', 10, 'Spielzeug', 100, '2024-12-31', '19e82d5f-6a85-4f3b-8396-c8f4b5c7e8a3');
-- Transaktionen (Ziel erreicht)
INSERT INTO stt_transactions (id, amount, date, savings_target_id)
VALUES ('b191e8f0-a760-49f6-8e4a-222c88c0727b', 10, '2024-01-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('c3d87497-8338-45ee-85f0-6a6bdd3e7b7b', 10, '2024-02-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('8782cc93-1758-4980-bcab-9106ee3048ce', 10, '2024-03-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('5c9bd888-5ef8-475a-8dbf-ec2cc2111f3f', 10, '2024-04-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('cd1b6e81-cea2-481e-88ea-424cd8d1cfd1', 10, '2024-05-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('adf8370b-b28e-42ab-b567-17c7b93e7901', 10, '2024-06-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('62d32d70-2ef5-43cc-b922-c83a960248e4', 10, '2024-07-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('24886ce1-f2b0-483a-a00e-30337ded95a7', 10, '2024-08-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('98199816-d4f7-4f05-9e8c-f49eeafc494e', 10, '2024-09-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11'),
       ('c41bc5af-e750-4086-94a6-23c1e214f3f0', 10, '2024-10-01', '622cf28d-18cb-4cc7-ad77-ea86aad55a11');