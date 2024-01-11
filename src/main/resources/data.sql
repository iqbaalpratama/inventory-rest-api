INSERT INTO products (name, description, price, stock) VALUES ('Luwak White Coffee', 'Best white coffee', 1500, 50);
INSERT INTO products (name, description, price, stock) VALUES ('Lux Body Wash', 'Best body wash', 10000, 25);
INSERT INTO products (name, description, price, stock) VALUES ('Le Minerale', 'Best water', 5000, 125);
INSERT INTO products (name, description, price, stock) VALUES ('Qtela', 'Best cassava chips', 7500, 15);

INSERT INTO roles(name) VALUES ('ADMIN');
INSERT INTO roles(name) VALUES ('CUSTOMER');

--password admin = admin123
INSERT INTO users(name, email, password) VALUES ('Admin', 'admin123@gmail.com', '$2a$12$IHBcT/bSmOsIq7mbJuHkj.nobDrNl.yOce5by1OqEBzN0LcrUzpJe'); 
--password customer = customer123
INSERT INTO users(name, email, password) VALUES ('Customer', 'customer123@gmail.com', '$2a$12$KgipWn/tycbaKDz2.AE.SO0sRGkgOt0L86NxeRQF.MhxOpgNbZEbC');

INSERT INTO user_roles(user_id, role_id) VALUES (1,1);
INSERT INTO user_roles(user_id, role_id) VALUES (2,2);