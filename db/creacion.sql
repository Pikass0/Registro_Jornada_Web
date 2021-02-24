CREATE TABLE Empleados
    (Id_empleado INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    PRIMARY KEY (id_empleado),
    Dni VARCHAR(9) NOT NULL unique,
    Nombre VARCHAR(30) NOT NULL,
    Apellidos VARCHAR(60),
    Email VARCHAR(60) NOT NULL UNIQUE,
    Password VARCHAR(30),
    Activo BOOLEAN DEFAULT true,
    Administrador BOOLEAN DEFAULT false,
    Jornada_semanal INT DEFAULT 40 )
;
CREATE TABLE Registros
    (Id_registro INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    PRIMARY KEY (Id_registro),
    Id_empleado INT NOT NULL,
    Dia DATE NOT NULL,
    Check_in TIME,
    Check_out TIME,
    FOREIGN KEY (Id_empleado) REFERENCES Empleados(Id_empleado))
;

INSERT INTO empleados (DNI, NOMBRE, APELLIDOS, EMAIL, PASSWORD, JORNADA_SEMANAL) VALUES ('12312312L', 'Test', 'Testapellido', 't@c2b.com', '1234', 40);
INSERT INTO REGISTROS (ID_EMPLEADO, DIA, CHECK_IN, CHECK_OUT) VALUES (1, '2020-02-11', '02:02:00', '09:02:00');
