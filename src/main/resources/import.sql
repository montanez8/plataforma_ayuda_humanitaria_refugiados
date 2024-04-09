-- Insertar datos en la tabla Sede
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Dadaab','Condado de Dadaab, Kenia', 'Sr. John ONeill');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Kakuma','Condado de Turkana, Kenia', 'Sra. Angelina Jolie');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Bidibidi','Yumbe, Uganda', 'Sr. Arnauld Akodjenou');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Kutupalong','Coxs Bazar Bangladés', 'Sr. Filippo Grandi');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Zaatari','Gobernación de Mafraq, Jordania', 'Sr. Andrew Harper');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Moria','Isla de Lesbos, Grecia', 'Sra. Catherine Hamon');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Domiz','Dohuk, Iraq', 'Sr. Jean-Nicolas Beuze');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Yida','Provincia de Jizan, Arabia Saudita', 'Sr. Amin Awad');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Maiwut','Estado de Unity, Sudán del Sur', 'Sr. Toby Lanzer');
INSERT INTO sede (nombre,domicilio, director) VALUES ('Campo de Refugiados de Palestinia','Gobernación de Damasco, Siria', 'Sr. Pierre Krähenbühl');

-- Insertar datos en la tabla Socio
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Carlos Morales', '1234567890', '2023-04-01', 'MINIMA', 1);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Miguel Fuentes', '0987654321', '2023-05-15', 'MEDIA', 2);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Maria Urrutia', '5555555555', '2023-03-20', 'MAXIMA', 3);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Ana Polonia', '1111111111', '2023-06-10', 'MINIMA', 1);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Oscar Perez', '2222222222', '2023-07-05', 'MEDIA', 2);

--- INSERT con campo "profesion"

INSERT INTO voluntario (nombre, tipo_voluntario, profesion, disponibilidad, numero_trabajos) VALUES ('Jacinto Rodriguez', 'SANITARIO', 'medico', true, 5);
INSERT INTO voluntario (nombre, tipo_voluntario, profesion, disponibilidad, numero_trabajos) VALUES ('Polo Moreno', 'ADMINISTRATIVO', 'abogado', false, 3);
INSERT INTO voluntario (nombre, tipo_voluntario, profesion, disponibilidad, numero_trabajos) VALUES ('Francia Marquez', 'SANITARIO', 'enfermera', true, 8);
INSERT INTO voluntario (nombre, tipo_voluntario, profesion, disponibilidad, numero_trabajos) VALUES ('Yurley Galvis', 'ADMINISTRATIVO', 'contadora', true, 2);
INSERT INTO voluntario (nombre, tipo_voluntario, profesion, disponibilidad, numero_trabajos) VALUES ('Maria Ochoa', 'SANITARIO', 'psicologa', false, 6);



-- Insertar datos en la tabla Envio
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV001', 'Kenia', '2023-04-10');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV002', 'Uganda', '2023-05-20');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV003', 'Grecia', '2023-06-15');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV004', 'Sudán del Sur', '2023-07-05');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV005', 'siria', '2023-08-01');

INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Alimentos', 1000, 1);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Medicamentos', 500, 2);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Ropa', 2000, 3);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Agua potable', 1500, 4);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Artículos de higiene', 1200, 5);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Kits de higiene', 300, 1);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Kits de primeros auxilios', 150, 2);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Tiendas de campaña', 50, 3);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Utensilios de cocina', 200, 4);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Lámparas solares', 100, 5);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Mantas térmicas', 500, 1);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Bidones de agua', 400, 2);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Mochilas de supervivencia', 350, 3);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Sacos de dormir', 100, 4);
INSERT INTO material (nombre, cantidad, envio_id) VALUES ('Equipos de purificación de agua', 50, 5);

-- Insertar datos en la tabla envio_sede
INSERT INTO envio_sede (envio_id, sede_id) VALUES (1, 1);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (1, 2);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (2, 3);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (2, 3);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (3, 6);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (3, 3);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (4, 9);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (5, 10);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (5, 3);

-- Insertar datos en la tabla envio_voluntario
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (1, 1);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (1, 3);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (2, 2);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (2, 4);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (3, 1);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (3, 5);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (4, 3);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (4, 4);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (5, 2);
INSERT INTO envio_voluntario (envio_id, voluntario_id) VALUES (5, 5);

-- Insertar datos en la tabla voluntario_sedes
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (1, 1);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (1, 2);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (2, 2);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (3, 1);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (3, 3);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (4, 2);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (4, 3);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (5, 1);
INSERT INTO voluntario_sede (voluntario_id, sede_id) VALUES (5, 3);

-- Insertar datos en la tabla rol
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_DIRECTOR');
INSERT INTO roles (name) VALUES ('ROLE_AUXILIAR');

INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$nnOycCfiQLNxIfiCQxQXsO3M0rqSwdcXVsNWhwwKqyHFkJfoYlnoK', true);
INSERT INTO users (username, password, enabled) VALUES ('director', '$2a$10$Js9RxyNCIJncEfMlrL2Efu0IyQAtSIjZJ2jB2FytDb.q/w9EcUjQ2', true);
INSERT INTO users (username, password, enabled) VALUES ('auxiliar', '$2a$10$SxmmQXaWhtOp2jQdMpiEX.r7IRbu66KyEYmmgtFse1fJUmURNZHCe', true);

INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);
INSERT INTO users_roles (role_id, user_id) VALUES (2, 2);
INSERT INTO users_roles (role_id, user_id) VALUES (3, 3);