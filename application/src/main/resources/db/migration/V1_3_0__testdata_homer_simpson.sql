-- Benutzer 1: Homer Simpson
INSERT INTO stt_savings_targets (id, monthly_savings, name, target_amount, target_date, user_id)
VALUES ('d123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a', 100, 'Urlaub auf Hawaii', 2000, '2024-12-31', 'c54e6bfc-9f1f-4d02-ae74-798dbba75a71');
-- Transaktionen (kontinuierliche Einzahlungen, Ziel erreicht)
INSERT INTO stt_transactions (id, amount, date, savings_target_id)
VALUES ('8ab01e36-e9e9-46b4-a689-1049ec3f4a13', 100, '2024-01-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('12adcde6-fa0c-4c59-b33e-6ef9d7e0f546', 100, '2024-02-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('f4192f93-356f-4ede-86c6-b3eaa8c862c7', 100, '2024-03-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('c537d084-5365-4cd0-9cad-9926111afdd2', 100, '2024-04-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('abb8cdf0-607b-4a7b-8bf3-6089edc0133e', 100, '2024-05-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('8492dc1f-8a2e-4729-8949-a4d8efb1e616', 100, '2024-06-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('86f13646-0de7-4c89-ab18-b0c0fb77fba9', 100, '2024-07-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('d24b813f-3d04-4b5d-9b99-716e37a8bae0', 100, '2024-08-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('760a7584-b247-4858-aaf6-8f85987565cd', 100, '2024-09-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('945f16b1-634e-4f2f-9e23-d9768d45ac36', 100, '2024-10-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('59052fb3-f9a2-4734-a50e-b5a454bce83d', 100, '2024-11-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a'),
       ('bc9a0472-afbb-4e48-80fa-ad15844ab2a1', 100, '2024-12-01', 'd123f1a9-9f3d-4c8e-9b7a-3f7c2d1a6e5a');