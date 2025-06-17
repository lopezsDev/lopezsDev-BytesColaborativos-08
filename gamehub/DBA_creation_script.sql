-- On linux terminla => sudo -u postgres psql

-- first in youe terminal on your pgAdmin

CREATE DATABASE IF NOT EXISTS gamehub;

CREATE USER admin_gh WITH PASSWORD 'admin1234';

GRANT ALL PRIVILEGES ON DATABASE gamehub TO admin_gh;

/* connect to the database and run this allow this user more privileges on public scheme */

GRANT USAGE ON SCHEMA public TO admin_gh;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin_gh;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO admin_gh;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO admin_gh;

-- To grant privileges on future tables/sequences/functions created in public schema:
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO admin_gh;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO admin_gh;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON FUNCTIONS TO admin_gh;
-- Create tables on models