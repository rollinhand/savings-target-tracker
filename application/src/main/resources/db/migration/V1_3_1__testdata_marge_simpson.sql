-- Benutzer 2: Marge Simpson (Sonderzahlung)
INSERT INTO stt_savings_targets (id, monthly_savings, name, target_amount, target_date, user_id)
VALUES ('b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f', 50, 'Kunstunterricht', 1200, '2024-11-01', 'a987cbf2-bd19-4b9f-a2b1-d8f7e4b2a7a1');
-- Transaktionen (Einzahlungen und Sonderzahlungen, Ziel übererreicht)
INSERT INTO stt_transactions (id, amount, date, savings_target_id)
VALUES ('c80e8c4e-3588-4997-b465-ee0b39b6f611', 50, '2024-01-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('5e3c5f90-f9be-4efd-b49a-4f403c415575', 50, '2024-02-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('6b268301-3e44-499e-aa90-960f2f6ce881', 50, '2024-03-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('b054be9e-720e-4cb4-a8e7-268a90d07f33', 50, '2024-04-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('5dd89714-8203-41d5-ae3a-bc880e2ffb3c', 500, '2024-05-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'), -- Sonderzahlung
       ('53b86635-6eae-4f03-ac47-5a934f48b615', 50, '2024-06-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('fcf52add-1967-452b-8731-2ebf05acf322', 50, '2024-07-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('8f1573d3-4522-4723-bdb0-e8745a7fa741', 50, '2024-08-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'),
       ('36d2eafa-367c-4933-a6ce-0cfd9ee08703', 400, '2024-09-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f'), -- Sonderzahlung
       ('155a1bdf-a863-4b54-a854-46b002ec9b78', 50, '2024-10-01', 'b234f2b7-8e4d-4f7c-9a8f-3f9e2d6b7e8f');