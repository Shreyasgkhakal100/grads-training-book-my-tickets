ALTER TABLE movies ADD COLUMN duration INTEGER;
UPDATE movies SET duration = EXTRACT(EPOCH FROM (end_time - start_time));
ALTER TABLE movies DROP COLUMN end_time;
ALTER TABLE movies DROP COLUMN start_time;

