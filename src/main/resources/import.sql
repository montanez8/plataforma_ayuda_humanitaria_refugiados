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
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV001', 'Carrera 5 # 6-50, Barrio Centro,Caquetá', '2023-04-10');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV002', 'Calle 10 # 9-50, Barrio Centro,La Guajira', '2023-05-20');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV003', 'Calle 7 # 7-50, Cocuy ,Boyaca', '2023-06-15');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV004', 'El Charco, Nariño', '2023-07-05');
INSERT INTO envio (codigo, destino, fecha_envio) VALUES ('ENV005', 'El Bagre, Antioquia', '2023-08-01');

-- Insertar datos en la tabla envio_sede
INSERT INTO envio_sede (envio_id, sede_id) VALUES (1, 1);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (1, 2);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (2, 2);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (2, 3);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (3, 1);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (3, 3);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (4, 2);
INSERT INTO envio_sede (envio_id, sede_id) VALUES (5, 1);
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
INSERT INTO rol (name) VALUES ('Admin');
INSERT INTO rol (name) VALUES ('Director');
INSERT INTO rol (name) VALUES ('Auxiliar');

-- INSERT INTO usuario (username, password, enabled, admin) VALUES ('usuario1', 'contraseña1', true, false);
INSERT INTO usuario (username, password, enabled) VALUES ('Admin', 'admin', true);
-- INSERT INTO usuario (username, password, enabled, admin) VALUES ('usuario3', 'contraseña3', false, false);

INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);