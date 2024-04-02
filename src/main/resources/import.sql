-- Insertar datos en la tabla Sede
INSERT INTO sede (domicilio, director) VALUES ('Transversal 27 No. 100A-34, Barrio Ciudad Salitre , Bogota', 'sede principal');
INSERT INTO sede (domicilio, director) VALUES ('Avenida Boyacá No. 78-90, Barrio Kennedy', 'Sede Kennedy');
INSERT INTO sede (domicilio, director) VALUES ('Plaza Mayor', 'Sede Mayor');

-- Insertar datos en la tabla Socio
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Carlos Morales', '1234567890', '2023-04-01', 'MINIMA', 1);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Miguel Fuentes', '0987654321', '2023-05-15', 'MEDIA', 2);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Maria Urrutia', '5555555555', '2023-03-20', 'MAXIMA', 3);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Ana Polonia', '1111111111', '2023-06-10', 'MINIMA', 1);
INSERT INTO socio (nombre, cuenta_bancaria, fecha_pago, tipo_cuota, sede_id) VALUES ('Oscar Perez', '2222222222', '2023-07-05', 'MEDIA', 2);

-- Insertar datos en la tabla Voluntario
INSERT INTO voluntario (nombre, tipo_voluntario, disponibilidad, numero_trabajos) VALUES ('Jacinto Rodriguez', 'SANITARIO', true, 5);
INSERT INTO voluntario (nombre, tipo_voluntario, disponibilidad, numero_trabajos) VALUES ('Polo MOreno', 'ADMINISTRATIVO', false, 3);
INSERT INTO voluntario (nombre, tipo_voluntario, disponibilidad, numero_trabajos) VALUES ('Francia Marquez', 'SANITARIO', true, 8);
INSERT INTO voluntario (nombre, tipo_voluntario, disponibilidad, numero_trabajos) VALUES ('Yurley Galvis', 'ADMINISTRATIVO', true, 2);
INSERT INTO voluntario (nombre, tipo_voluntario, disponibilidad, numero_trabajos) VALUES ('Diana Ochoa', 'SANITARIO', false, 6);


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
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (1, 1);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (1, 2);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (2, 2);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (3, 1);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (3, 3);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (4, 2);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (4, 3);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (5, 1);
INSERT INTO voluntario_sedes (voluntario_id, sedes_id) VALUES (5, 3);