-- Створюємо кастомні типи ENUM,
-- щоб вони відповідали Java Enums з Лабораторної №2

-- 1. Enum для ApartmentClass
CREATE TYPE apartment_class_enum AS ENUM (
    'ECONOMY',
    'STANDARD',
    'BUSINESS',
    'LUXURY'
);

-- 2. Enum для BookingStatus
CREATE TYPE booking_status_enum AS ENUM (
    'PENDING',
    'APPROVED',
    'REJECTED',
    'PAID',
    'CANCELLED'
);


-- Створюємо таблицю для Client
CREATE TABLE clients (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         age INT
);

-- Створюємо таблицю для Room
CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       room_number VARCHAR(20) NOT NULL UNIQUE,
                       number_of_beds INT NOT NULL,
                       apartment_class apartment_class_enum NOT NULL,
                       is_available BOOLEAN DEFAULT TRUE,
                       price_per_night NUMERIC(10, 2) NOT NULL
);

-- Створюємо таблицю для BookingRequest (Заявка на бронювання)
CREATE TABLE booking_requests (
                                  id BIGSERIAL PRIMARY KEY,
                                  client_id BIGINT NOT NULL,
                                  number_of_beds INT NOT NULL,
                                  apartment_class apartment_class_enum NOT NULL,
                                  stay_start_time TIMESTAMP NOT NULL,
                                  stay_end_time TIMESTAMP NOT NULL,
                                  status booking_status_enum DEFAULT 'PENDING',

    -- Додаємо зовнішній ключ (Foreign Key) до таблиці clients
                                  CONSTRAINT fk_client
                                      FOREIGN KEY (client_id)
                                          REFERENCES clients(id)
);

-- Створюємо таблицю для Invoice (Рахунок)
CREATE TABLE invoices (
                          id BIGSERIAL PRIMARY KEY,
                          booking_request_id BIGINT NOT NULL UNIQUE, -- Рахунок унікальний для однієї заявки
                          client_id BIGINT NOT NULL,
                          room_id BIGINT NOT NULL,
                          amount NUMERIC(12, 2) NOT NULL,
                          issue_date DATE NOT NULL,
                          due_date DATE NOT NULL,
                          is_paid BOOLEAN DEFAULT FALSE,

    -- Зовнішні ключі
                          CONSTRAINT fk_booking_request
                              FOREIGN KEY (booking_request_id)
                                  REFERENCES booking_requests(id),

                          CONSTRAINT fk_client
                              FOREIGN KEY (client_id)
                                  REFERENCES clients(id),

                          CONSTRAINT fk_room
                              FOREIGN KEY (room_id)
                                  REFERENCES rooms(id)
);
