CREATE TABLE courses (
                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT,
                         category VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lessons (
                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         course_id BIGINT REFERENCES courses(id) ON DELETE CASCADE,
                         title VARCHAR(255) NOT NULL,
                         content TEXT,
                         video_url VARCHAR(255),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trigger function to update the updated_at column on row update
CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for the courses table
CREATE TRIGGER update_courses_timestamp
    BEFORE UPDATE ON courses
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- Trigger for the lessons table
CREATE TRIGGER update_lessons_timestamp
    BEFORE UPDATE ON lessons
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
