/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  idusuario VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (idusuario));
 
INSERT INTO usuario (idusuario, password) VALUES 
('usuario1', 'dXN1YXJpbzE='),
('usuario2', 'dXN1YXJpbzI=');

 /*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS libro;
CREATE TABLE libro (
  idlibro INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  cantidad INT NOT NULL,
  precio INT NOT NULL,
  imagen VARCHAR(100) NULL,
  PRIMARY KEY (idlibro));

INSERT INTO libro (titulo, cantidad, precio, imagen) VALUES
('Caliz de fuego', 96, '50000', 'calizDeFuego.jpg'),
('Camara Secreta', 80, '40000', 'camaraSecreta.jpg'),
('Misterio del principe', 70, '45000', 'misterioDelPrincipe.jpeg'),
('Orden del Fenix', 87, '60000', 'ordenDelFenix.jpg'),
('Piedra filosofal', 50, '35000', 'piedraFilosofal.jpg'),
('Prisionero de Azcaban', 79, '42000', 'prisioneroDeAzcaban.jpg');

/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS compra;
CREATE TABLE compra (
  idcompra INT NOT NULL AUTO_INCREMENT,
  idlibroPK INT NOT NULL,
  idusuarioPK VARCHAR(45) NULL,
  valorcompra INT NOT NULL,
  estado INT NOT NULL,
  cantidadcomprada INT NOT NULL,
  PRIMARY KEY (idcompra));

ALTER TABLE compra 
ADD CONSTRAINT idusuarioPK
  FOREIGN KEY (idusuarioPK)
  REFERENCES usuario (idusuario);
 
ALTER TABLE compra 
ADD CONSTRAINT idlibroPK
    FOREIGN KEY (idlibroPK)
    REFERENCES libro (idlibro);

INSERT INTO compra (idlibroPK, idusuarioPK, valorcompra, estado, cantidadcomprada) VALUES 
(1, 'usuario1', 100000, '1', 2),
(1, 'usuario1', 50000, '1', 1),
(4, 'usuario1', 180000, '1', 3),
(1, 'usuario1', 50000, '2', 1),
(6, 'usuario2', 42000, '1', 1);
/*-------------------------------------------------------------------------------------------*/