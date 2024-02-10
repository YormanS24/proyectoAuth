INSERT INTO main."role"(created_at, updated_at, role_id, role_name)VALUES(noW(), now(), 'aedaa76a-acfe-4267-b43d-d4798bc8f36b', 'administrador');
INSERT INTO main."role"(created_at, updated_at, role_id, role_name)VALUES(noW(), now(), '76d08367-ae60-4ba5-bb5f-b734361a63a4', 'usuarios');





------------------- USER - ROLE ----------------
INSERT INTO main.user_role(role_id, user_id)VALUES('aedaa76a-acfe-4267-b43d-d4798bc8f36b', 'f20a2b93-33f2-432a-8982-10fb2b956bdf');
INSERT INTO main.user_role(role_id, user_id)VALUES('76d08367-ae60-4ba5-bb5f-b734361a63a4', '6cf898ce-2d38-4372-b987-d5c9131d13d9');