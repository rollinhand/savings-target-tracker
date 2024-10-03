CREATE TABLE stt_savings_targets
(
    id              VARCHAR(255) NOT NULL,
    monthly_savings NUMERIC,
    name            VARCHAR(255),
    target_amount   NUMERIC,
    target_date     DATE,
    user_id         VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE stt_transactions
(
    id                 VARCHAR(255) NOT NULL,
    amount             NUMERIC,
    date               DATE,
    savings_target_id VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE stt_transactions
    ADD FOREIGN KEY (savings_target_id) REFERENCES stt_savings_targets (id);