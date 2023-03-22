ALTER TABLE order_hdr ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE order_hdr ALTER COLUMN ver SET DEFAULT 0;
ALTER TABLE order_dtl ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE order_dtl ALTER COLUMN ver SET DEFAULT 0;

INSERT INTO order_hdr (id, customer_name, employee_id, trx_code, grand_total, created_by, created_at) VALUES
('84b6f8ea-f107-4ea3-9514-482e74179f56', 'maria', '846b92a3-d694-4ca1-a01e-7879ca1887f1', 'TRX-2222222', 160500, '846b92a3-d694-4ca1-a01e-7879ca1887f1', NOW());

INSERT INTO order_dtl (id, order_hdr_id, product_id, quantity, sub_total, created_by, created_at) VALUES
('8c2ce04c-b5ff-44b5-88db-c17741072c10', '84b6f8ea-f107-4ea3-9514-482e74179f56', '846b92a3-d694-4ca1-a01e-7879ca1887f1', 3, 30000, '846b92a3-d694-4ca1-a01e-7879ca1887f1', NOW()),
('957f7f21-2205-445d-a534-94d9ce0fe56e', '84b6f8ea-f107-4ea3-9514-482e74179f56', '34924351-7d39-4a7a-a359-e96cb84f78cc', 3, 22500, '846b92a3-d694-4ca1-a01e-7879ca1887f1', NOW()),
('fb0d780e-0d51-4d51-b208-4e60ee3c95e9', '84b6f8ea-f107-4ea3-9514-482e74179f56', '4612287d-84ce-48ce-ba3f-7edc2af36820', 3, 45000, '846b92a3-d694-4ca1-a01e-7879ca1887f1', NOW()),
('bee374a0-6873-4962-94b8-3108facbd85b', '84b6f8ea-f107-4ea3-9514-482e74179f56', '888e748b-12be-4525-8ca5-70650f40bb7a', 3, 63000, '846b92a3-d694-4ca1-a01e-7879ca1887f1', NOW());

ALTER TABLE order_hdr ALTER COLUMN is_active DROP DEFAULT;
ALTER TABLE order_hdr ALTER COLUMN ver DROP DEFAULT;
ALTER TABLE order_dtl ALTER COLUMN is_active DROP DEFAULT;
ALTER TABLE order_dtl ALTER COLUMN ver DROP DEFAULT;