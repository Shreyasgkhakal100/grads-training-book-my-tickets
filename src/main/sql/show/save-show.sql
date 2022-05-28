INSERT INTO shows(title, movie_id, start_time, end_time)
VALUES (:title, :movie_id, :start_time, :end_time)
returning *;
