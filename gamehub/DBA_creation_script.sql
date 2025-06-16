-- On linux terminla => sudo -u postgres psql

-- first in youe terminal on your pgAdmin

CREATE DATABASE IF NOT EXISTS gameHub

CREATE USER admin_gh WITH PASSWORD 'admin1234'

GRANT ALL PRIVILEGES ON DATABASE gameHub TO admin_gh;

-- Create tables on models