-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


CREATE TABLE IF NOT EXISTS auth_role (
                                         auth_role_id SERIAL PRIMARY KEY,
                                         role_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS auth_user (
                                         auth_user_id SERIAL PRIMARY KEY,
                                         username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role_id BIGINT NOT NULL,
                                          PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES auth_user (auth_user_id),
    FOREIGN KEY (role_id) REFERENCES auth_role (auth_role_id)
    );

-- Insert initial roles
INSERT INTO auth_role (role_name) VALUES ('USER'), ('ADMIN'), ('SUPERADMIN')
    ON CONFLICT DO NOTHING;

-- Optional: Create a test user with USER role
INSERT INTO auth_user (username, password) VALUES ('testuser', 'password')
    ON CONFLICT DO NOTHING;

-- -- Get the IDs for the test user and USER role
-- DO $$
-- DECLARE
-- user_id BIGINT;
--     role_id BIGINT;
-- BEGIN
-- SELECT auth_user_id INTO user_id FROM auth_user WHERE username = 'testuser';
-- SELECT auth_role_id INTO role_id FROM auth_role WHERE role_name = 'USER';
--
-- IF user_id IS NOT NULL AND role_id IS NOT NULL THEN
--         INSERT INTO user_roles (user_id, role_id) VALUES (user_id, role_id)
--         ON CONFLICT DO NOTHING;
-- END IF;
-- END $$;