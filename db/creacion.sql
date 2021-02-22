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
