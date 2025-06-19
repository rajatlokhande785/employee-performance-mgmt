-- DEPARTMENTS
INSERT INTO department (id, name, budget) VALUES (1, 'Engineering', 1000000.0);
INSERT INTO department (id, name, budget) VALUES (2, 'HR', 300000.0);

-- PROJECTS
INSERT INTO project (id, name, start_date, end_date, department_id) VALUES
(1, 'Project Alpha', '2023-01-01', '2023-12-31', 1),
(2, 'Project Beta', '2023-06-01', '2023-11-30', 1);

-- EMPLOYEES
INSERT INTO employee (id, name, email, date_of_joining, salary, department_id, manager_id) VALUES
(1, 'Alice', 'alice@example.com', '2022-03-01', 70000.0, 1, NULL),
(2, 'Bob', 'bob@example.com', '2023-04-15', 50000.0, 1, 1),
(3, 'Eve', 'eve@example.com', '2023-02-10', 45000.0, 2, NULL);

-- PERFORMANCE REVIEWS
INSERT INTO performance_review (id, employee_id, review_date, score, review_comments) VALUES
(1, 2, '2023-12-01', 4, 'Good work'),
(2, 2, '2024-01-01', 5, 'Excellent'),
(3, 2, '2024-06-01', 3, 'Needs improvement');

-- EMPLOYEE_PROJECT (many-to-many mapping)
INSERT INTO employee_project (employee_id, project_id, assigned_date, role) VALUES
(2, 1, '2023-05-01', 'Developer'),
(2, 2, '2023-06-15', 'Tester');