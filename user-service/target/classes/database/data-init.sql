ALTER TABLE roles ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE roles ALTER COLUMN ver SET DEFAULT 0;
ALTER TABLE users ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE users ALTER COLUMN ver SET DEFAULT 0;

INSERT INTO roles (id, code, name, created_by, created_at) VALUES
('a289e5a6-6736-407b-8ef6-090773077422', 'RLSYS', 'System', '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('d836da5e-a364-43ad-aafd-a38ca9763fd4', 'RLEMP', 'Employees', '846b92a3-d694-4ca1-a01e-7879ca1887f1', now());

INSERT INTO users (id, username, email, password, fullname, role_id, created_by, created_at) VALUES
('846b92a3-d694-4ca1-a01e-7879ca1887f1', 'system', 'system@rezimaulana.link', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'System', 'a289e5a6-6736-407b-8ef6-090773077422', '846b92a3-d694-4ca1-a01e-7879ca1887f1', now()),
('d94a8f11-ae2b-40e2-aeca-c928dff44b89', 'rezimaulana', 'rsazrm@gmail.com', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'Maulana Rezi', 'd836da5e-a364-43ad-aafd-a38ca9763fd4', '846b92a3-d694-4ca1-a01e-7879ca1887f1', now());

ALTER TABLE roles ALTER COLUMN is_active DROP DEFAULT;
ALTER TABLE roles ALTER COLUMN ver DROP DEFAULT;
ALTER TABLE users ALTER COLUMN is_active DROP DEFAULT;
ALTER TABLE users ALTER COLUMN ver DROP DEFAULT;