CREATE TABLE customers (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nit CHAR(9) NOT NULL UNIQUE,
    phone_number CHAR(8) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);
