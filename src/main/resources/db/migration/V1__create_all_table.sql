CREATE TABLE IF NOT EXISTS "movie" (
    id SERIAL NOT NULL,
    title varchar(400) NOT NULL,
    description varchar(2048),
    rating float4,
    image varchar(1024),
    created_at timestamptz default now(),
    updated_at timestamptz,
    primary key(id)
);
