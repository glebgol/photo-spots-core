CREATE TABLE tags (
    id SERIAL PRIMARY KEY,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    description TEXT,
    imageUri TEXT
);
