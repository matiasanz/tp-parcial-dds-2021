#inserto locales

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(0,'Av caseros 123','Mi Barrio Pizzeria') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Av Chiclana 2343','El Noble') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Monteagudo 345','Rotiseria Martinez') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(0,'Saenz 4567','La Fachada') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(10,'Famatina 234','Grido') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Jose c Paz 1293','El Secreto de la Milanesa') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(10,'Colombre 3445','Chocorissimo') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(1,'Inclan 3456','Rotiseria Lo de Gustavo') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(0,'Cochabamba 998','Kentucky') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(10,'Av La Rioja 1783','Nicolo') ;

INSERT INTO pedidosYa.locales(categoria,direccion,nombre)
VALUES(10,'Av Rivadavia 2336','Faricci') ;

#inserto categorias clientes

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('p');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('o');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('f');

INSERT INTO pedidosYa.categoriasclientes(detalle)
VALUES('h');

#menu rotiserias
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

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',700,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Fugazzeta',980,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Cuatro Quesos',670,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon y Morrones',1000,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Napolitana',899,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Capresse',567,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza de Anana',950,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon Crudo y Rucula',1000,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Palmitos',789,1);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Champignones',800,1);

#menu rotiserias
INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',980,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pollo al Spiedo',0.25,'Pollo al Spiedo',500,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Filet de Merluza',0.25,'Filet de merluza',300,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Rabas',0.50,'Rabas a la Romana',1000,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Supremas de pollo con queso',0.25,'Supremas a la Fugazza',400,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Papas Fritas',0,'Papas Fritas con Guacamole',500,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Costillas de cerdo',0.50,'Costillas de Cerdo Grill',800,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Albondigas',0.50,'Albondigas Mortales',1000,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ñoquis',0.50,'Ñoquis de la Casa',300,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Bifes a la Criolla',0.50,'Bifes a la Criolla',700,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ravioles',0.25,'Ravioles de Verdura',850,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos al pastor',100,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos de Verdura',100,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Zapallitos',0,'Zapallitos Rellenos',100,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tortilla de Papas',0,'Tortilla Española',250,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salmon Rosado',0,'Salmon',2500,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tarta de Jamon y Queso',0,'Tarta de JyQ',800,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Mollejas al Verdeo',0.25,'Mollejas al Verdeo',1000,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Riñoncitos a la Provenzal',0.25,'Riñoncitos a la Provenzal',1340,2);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',980,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pollo al Spiedo',0.25,'Pollo al Spiedo',500,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Filet de Merluza',0.25,'Filet de merluza',300,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Rabas',0.50,'Rabas a la Romana',1000,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Supremas de pollo con queso',0.25,'Supremas a la Fugazza',400,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Papas Fritas',0,'Papas Fritas con Guacamole',500,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Costillas de cerdo',0.50,'Costillas de Cerdo Grill',800,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Albondigas',0.50,'Albondigas Mortales',1000,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ñoquis',0.50,'Ñoquis de la Casa',300,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Bifes a la Criolla',0.50,'Bifes a la Criolla',700,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ravioles',0.25,'Ravioles de Verdura',850,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos al pastor',100,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos de Verdura',100,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Zapallitos',0,'Zapallitos Rellenos',100,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tortilla de Papas',0,'Tortilla Española',250,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salmon Rosado',0,'Salmon',2500,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tarta de Jamon y Queso',0,'Tarta de JyQ',800,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Mollejas al Verdeo',0.25,'Mollejas al Verdeo',1000,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Riñoncitos a la Provenzal',0.25,'Riñoncitos a la Provenzal',1340,6);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',980,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pollo al Spiedo',0.25,'Pollo al Spiedo',500,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Filet de Merluza',0.25,'Filet de merluza',300,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Rabas',0.50,'Rabas a la Romana',1000,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Supremas de pollo con queso',0.25,'Supremas a la Fugazza',400,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Papas Fritas',0,'Papas Fritas con Guacamole',500,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Costillas de cerdo',0.50,'Costillas de Cerdo Grill',800,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Albondigas',0.50,'Albondigas Mortales',1000,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ñoquis',0.50,'Ñoquis de la Casa',300,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Bifes a la Criolla',0.50,'Bifes a la Criolla',700,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Ravioles',0.25,'Ravioles de Verdura',850,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos al pastor',100,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tacos',0,'Tacos de Verdura',100,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Zapallitos',0,'Zapallitos Rellenos',100,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tortilla de Papas',0,'Tortilla Española',250,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Salmon Rosado',0,'Salmon',2500,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Tarta de Jamon y Queso',0,'Tarta de JyQ',800,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Mollejas al Verdeo',0.25,'Mollejas al Verdeo',1000,8);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Riñoncitos a la Provenzal',0.25,'Riñoncitos a la Provenzal',1340,8);

#menu heladerias
INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate',500,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Vainilla',300,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Dulce de Leche',350,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Crema Americana',200,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Granizado',500,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Menta',600,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Banana Split',700,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Frutilla',500,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Agua',0.5,'Limon',500,5);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate Suizo',300,5);


INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate',500,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Vainilla',300,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Dulce de Leche',350,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Crema Americana',200,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Granizado',500,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Menta',600,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Banana Split',700,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Frutilla',500,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Agua',0.5,'Limon',500,7);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate Suizo',300,7);


INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate',500,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Vainilla',300,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Dulce de Leche',350,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Crema Americana',200,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Granizado',500,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Menta',600,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Banana Split',700,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Frutilla',500,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Agua',0.5,'Limon',500,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate Suizo',300,10);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate',500,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Vainilla',300,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Dulce de Leche',350,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Crema Americana',200,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Granizado',500,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Menta',600,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Banana Split',700,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Frutilla',500,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Agua',0.5,'Limon',500,11);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Helado de Crema',0.5,'Chocolate Suizo',300,11);

#menu pizerias
INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',700,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Fugazzeta',980,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Cuatro Quesos',670,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon y Morrones',1000,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Napolitana',899,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Capresse',567,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza de Anana',950,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon Crudo y Rucula',1000,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Palmitos',789,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Champignones',800,4);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Muzzarella',700,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Fugazzeta',980,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Cuatro Quesos',670,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon y Morrones',1000,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Napolitana',899,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Capresse',567,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza de Anana',950,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Jamon Crudo y Rucula',1000,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Palmitos',789,9);

INSERT INTO pedidosYa.platos(tipo_plato,descripcion,descuento,nombre,precioBase,local)
VALUES('p','Pizza a la piedra',0.5,'Pizza Champignones',800,9);

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
INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(1,'pizza');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(1,'queso');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(1,'oregano');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(1,'aceitunas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(2,'pollo');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(2,'mostaza');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(3,'pescado');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(3,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(3,'pan rallado');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'rabas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'oregano');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'queso');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'pan rayado');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'ajo');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(4,'perejil');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(5,'papas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(5,'manteca');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(5,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(5,'queso');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(5,'leche');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(6,'supremas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(6,'pan rayado');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(6,'queso fresco');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(7,'papas con cascara,sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(7,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'papas con cascara');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'rabas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'oregano');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'queso');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'pan rayado');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'ajo,');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(8,'perejil');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'pollo');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'mostaza');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'papas');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'manteca');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'sal');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'queso');

INSERT INTO pedidosYa.ingredientexplato(plato,ingrediente)
VALUES(9,'leche');

#inserto usuarios
INSERT INTO pedidosYa.usuarios(tipo,apellido,mail,nombre,password,username,local_id)
VALUES('d','Martinez','rm@gmail.com','romina','123','romi',3);

INSERT INTO pedidosYa.mediosdecontacto(medio,usuario)
VALUES ('push',1);

INSERT INTO pedidosYa.mediosdecontacto(medio,usuario)
VALUES ('mail',1);

INSERT INTO pedidosYa.usuarios(tipo,apellido,mail,nombre,password,username,categoria)
VALUES('c','Anzorandia','manz@gmail.com','matias','123','mati',1);

INSERT INTO pedidosYa.mediosdecontacto(medio,usuario)
VALUES ('mail',2);

INSERT INTO pedidosYa.mediosdecontacto(medio,usuario)
VALUES ('push',2);

#inserto direcciones conocidas de cliente matias
INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(1,'Uspallata 300');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(1,'Chiclana 876');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(1,'Montes de Oca 4256');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(2,'Caseros 300');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(2,'Saenz 8746');

INSERT INTO pedidosYa.direccionxcliente(cliente,direccion)
VALUES(2,'Jujuy 8536');

UPDATE pedidosYa.platos
	SET fechaCreacion = '2017-06-15'
    where id>=0
;