ALTER TABLE products ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE products ALTER COLUMN ver SET DEFAULT 0;

INSERT INTO products (id, name, price, quantity, created_by, created_at) VALUES
('846b92a3-d694-4ca1-a01e-7879ca1887f1', 'OBH', 10000, 97, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('34924351-7d39-4a7a-a359-e96cb84f78cc', 'Panadol', 7500, 97, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('4612287d-84ce-48ce-ba3f-7edc2af36820', 'Expresso', 15000, 97, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('d6a79d47-6405-47a8-aae3-6ba12d50c2d0', 'Americano', 23000, 100, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('ca78a409-1c5c-437c-ba33-0ea721250e62', 'Vanilla Latte', 17500, 100, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('888e748b-12be-4525-8ca5-70650f40bb7a', 'Hot Chocolate', 21000, 97, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('c2987008-3e12-4428-8823-8b3acd848f19', 'Strawbery Milshake', 23250, 100, '846b92a3-d694-4ca1-a01e-7879ca1887f1', now());

ALTER TABLE products ALTER COLUMN is_active DROP DEFAULT;
ALTER TABLE products ALTER COLUMN ver DROP DEFAULT;