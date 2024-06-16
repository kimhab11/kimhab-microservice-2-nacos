CREATE TABLE enrollments (
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             user_id BIGINT REFERENCES users(id),
                             course_id BIGINT REFERENCES courses(id),
                             enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE progress (
                          id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          enrollment_id BIGINT REFERENCES enrollments(id) ON DELETE CASCADE,
                          lesson_id BIGINT REFERENCES lessons(id),
                          completed BOOLEAN DEFAULT FALSE,
                          completed_at TIMESTAMP
);
