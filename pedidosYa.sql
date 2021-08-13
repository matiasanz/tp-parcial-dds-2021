#inserto locales

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Av caseros 123','Mi Barrio Pizzeria') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(3,'Av Chiclana 2343','El Noble') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(2,'Monteagudo 345','Rotiseria Martinez') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Saenz 4567','La Fachada') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(5,'Famatina 234','La Vendetta') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(3,'Jose c Paz 1293','El Secreto de la Milanesa') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(3,'Colombre 3445','Dean & Dennys') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(7,'Inclan 3456','Rotiseria Casa China') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Cochabamba 998','Kentucky') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(2,'Av La Rioja 1783','La Farola') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(3,'Av Rivadavia 2336','Faricci') ;

#inserto categorias clientes

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('p');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('o');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('f');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('h');

#inserto platos
INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',980,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pollo al Spiedo',0.25,'Pollo al Spiedo',500,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Filet de Merluza',0.25,'Filet de merluza',300,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Rabas',0.50,'Rabas a la Romana',1000,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pure',0.25,'Pure de Papas',350,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Supremas de pollo con queso',0.25,'Supremas a la Fugazza',400,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Papas Fritas',0,'Papas Fritas sin sal',500,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,local)
VALUES('c','Rabas a la Romana con Papas Fritas',0.25,'Rabas a la Romana con Fritas',3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,local)
VALUES('c','Pollo al Spiedo con Papas Fritas',0.25,'Pollo al Spiedo con Papas Fritas',3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Costillas de cerdo',0.50,'Costillas de Cerdo Grill',800,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Albondigas',0.50,'Albondigas Mortales',1000,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ñoquis',0.50,'Ñoquis de la Casa',300,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Bifes a la Criolla',0.50,'Bifes a la Criolla',700,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ravioles',0.25,'Ravioles de Verdura',850,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salsa Blanca',0,'Salsa Blanca',100,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salsa Estofado',0,'Salsa Estofado',100,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salsa York',0,'Salsa York',100,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,local)
VALUES('c','Ravioles a la York',0,'Ravioles a la York',3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,local)
VALUES('c','Costillas de Cerdo con Salsa Blanca y Pure',0,'Costillas Power',3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tortilla de Papas',0,'Tortilla Española',250,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salmon Rosado',0,'Salmon',2500,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tarta de Jamon y Queso',0,'Tarta de JyQ',800,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Mollejas al Verdeo',0.25,'Mollejas al Verdeo',1000,3);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Riñoncitos a la Provenzal',0.25,'Riñoncitos a la Provenzal',1340,3);

#inserto platoxcombo
INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(8,4);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(8,7);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(9,2);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(9,5);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(19,10);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(19,15);

INSERT INTO pedidosYa.platoxcombo(combo,platos_id)
VALUES(19,5);

#inserto ingredientes a los platos
INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(1,'pizza,queso,oregano,aceitunas');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(2,'pollo, mostaza');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(3,'pescado, sal, pan rallado');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(4,'rabas, oregano, queso, sal, pan rayado,ajo,perejil');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(5,'papas, manteca, sal, queso, leche');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(6,'supremas, pan rayado, queso fresco');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(7,'papas con cascara,sal');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(8,'papas con cascara,sal,rabas, oregano, queso, sal, pan rayado,ajo,perejil');

INSERT INTO pedidosYa.ingredientesxplato(plato,ingredientes)
VALUES(9,'pollo, mostaza, papas, manteca, sal, queso, leche');

#inserto usuarios
INSERT INTO pedidosYa.usuarios(tipo,apellido,mail,nombre,password,username,local_id)
VALUES('d','Martinez','rm@gmail.com','romina','123','romi',3);

INSERT INTO pedidosYa.mediodecontacto(medio,cliente)
VALUES ('push',1);

INSERT INTO pedidosYa.mediodecontacto(medio,cliente)
VALUES ('mail',1);

INSERT INTO pedidosYa.usuarios(tipo,apellido,mail,nombre,password,username,categoria)
VALUES('c','Anzorandia','manz@gmail.com','matias','123','mati',1);

INSERT INTO pedidosYa.mediodecontacto(medio,cliente)
VALUES ('mail',2);

INSERT INTO pedidosYa.mediodecontacto(medio,cliente)
VALUES ('push',2);

#inserto direcciones conocidas de cliente matias
INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(1,'Uspallata 300');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(1,'Chiclana 876');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(1,'Montes de Oca 4256');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(2,'Caseros 300');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(2,'Saenz 8746');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccionesConocidas)
VALUES(2,'Jujuy 8536');