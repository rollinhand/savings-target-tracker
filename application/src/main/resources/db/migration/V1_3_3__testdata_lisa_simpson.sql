-- Benutzer 4: Lisa Simpson (Ziel übererreicht)
INSERT INTO stt_savings_targets (id, monthly_savings, name, target_amount, target_date, user_id)
VALUES ('e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f', 150, 'Wissenschaftskonferenz', 1000, '2024-09-01', 'e7bcf7a8-42ff-4e67-b4ff-bb5d3f1c69a2');
-- Transaktionen (Einzahlungen und Sonderzahlungen, Ziel übererreicht)
INSERT INTO stt_transactions (id, amount, date, savings_target_id)
VALUES ('987a867f-92ff-47c8-89c1-09f894b8c4f4', 150, '2024-01-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f'),
       ('49dba3f1-70eb-41ec-b2b6-bb0810125650', 150, '2024-02-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f'),
       ('ff8e91d1-2a16-4601-80c8-97b06e8fc08a', 300, '2024-03-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f'), -- Sonderzahlung
       ('f07ab088-509d-4a4f-b39c-ff7747a5f83c', 150, '2024-04-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f'),
       ('8edf0090-0208-4cc0-bcb7-095690541ff0', 150, '2024-05-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f'),
       ('a6c47dfd-6872-4088-b68f-1d00bb443dfa', 150, '2024-06-01', 'e456f4d9-6e4d-4e7f-9b8f-4f7c3f9b8e7f');