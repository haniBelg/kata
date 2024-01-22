-- src/test/resources/data.sql
delete from medical_practitioners;
delete from medical_patients;
delete from medical_events;
-- Insert practitioners
INSERT INTO medical_practitioners (id, email, is_open_for_new_patients, allow_over_booking) VALUES
(1, 'practitioner1@example.com', true, true),
(2, 'practitioner2@example.com', true, false),
(3, 'practitioner3@example.com', false, true);

INSERT INTO medical_patients (id, email) VALUES
(1, 'patient1@example.com'),
(2, 'patient2@example.com'),
(3, 'patient3@example.com');

-- Insert events for practitioner 1 (appointments)
INSERT INTO medical_events (id, start_date_time, duration, practitioner_id, patient_id, event_type) VALUES
-- Week 1
(1, '2024-01-21T08:00:00Z', 'PT30M', 1, 1, 'APPOINTMENT'),
(2, '2024-01-21T09:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
(3, '2024-01-21T10:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
-- Week 2
(4, '2024-01-28T08:00:00Z', 'PT30M', 1, 2, 'APPOINTMENT'),
(5, '2024-01-28T09:00:00Z', 'PT30M', 2, 2, 'APPOINTMENT'),
(6, '2024-01-28T10:00:00Z', 'PT30M', 3, 2, 'APPOINTMENT'),
-- Week 3
(7, '2024-02-04T08:00:00Z', 'PT30M', 1, 3, 'APPOINTMENT'),
(8, '2024-02-04T09:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
(9, '2024-02-04T10:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
-- Week 4
(10, '2024-02-11T08:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
(11, '2024-02-11T09:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),
(12, '2024-02-11T10:00:00Z', 'PT30M', 1, NULL, 'APPOINTMENT'),

-- Week 1
(13, '2024-01-21T08:00:00Z', 'PT30M', 1, 1, 'OPENING'),
(14, '2024-01-21T09:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
(15, '2024-01-21T10:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
-- Week 2
(16, '2024-01-28T08:00:00Z', 'PT30M', 1, 2, 'OPENING'),
(17, '2024-01-28T09:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
(18, '2024-01-28T10:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
-- Week 3
(19, '2024-02-04T08:00:00Z', 'PT30M', 1, 3, 'OPENING'),
(20, '2024-02-04T09:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
(21, '2024-02-04T10:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
-- Week 4
(22, '2024-02-11T08:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
(23, '2024-02-11T09:00:00Z', 'PT30M', 1, NULL, 'OPENING'),
(24, '2024-02-11T10:00:00Z', 'PT30M', 1, NULL, 'OPENING');