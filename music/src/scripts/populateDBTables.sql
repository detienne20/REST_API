/*
The following is an example of a "Stored Procedure"
*/

INSERT INTO customers (fname, lname, username,email) VALUES ("Dr. P", "Brown", "prbrown", "philippa.brown");
INSERT INTO customers (fname, lname, username,email) VALUES ("Dania", "Etienne", "detienne20", "etienne.dania"); 
INSERT INTO customers (fname, lname, username,email) VALUES ("Nickan", "Hussaini", "NHussaini", "hussaini.nickan");

SELECT * FROM customers;

DROP PROCEDURE IF EXISTS insertProducts;

DELIMITER //
CREATE PROCEDURE insertProducts
(IN numProducts INT)

BEGIN
 DECLARE x, upc INT;
 DECLARE regPrice, salePrice DECIMAL(6,2);
 DECLARE prodName, prodType, prodColor, prodSize, descr, brand, gender  VARCHAR(255);
 
 SET x = 1;

 WHILE x  <= numProducts DO
	 SET prodType = ELT(MOD(x,6)+1, "pants", "shorts", "shirt", "sweater", "sandal", "boot");
	 SET prodColor = ELT(MOD(x,5)+1, "Blue", "Red", "Black", "Orange", "White");
	 SET prodSize = ELT(MOD(x,4)+1, "XSmall","Small", "Medium", "Large");
	 SET descr = ELT(MOD(x,3)+1, "Stylish", "Modern", "Classic");
	 SET gender = ELT(MOD(x,2)+1, "Female", "Male");
	 SET brand = "Generic";

	 SET prodName = CONCAT(gender,"'s ",prodType);
	 SET descr = CONCAT(descr, " ",prodSize," ", prodColor, " ",  prodName); 
	 SET regPrice = RAND()*(2000-1);
	 SET salePrice = regPrice * 0.75;
	 SET upc = FLOOR(RAND()*(999999999-100000000));	 

	 INSERT INTO products (name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender) 
		VALUES (prodName, regPrice, salePrice, upc, descr, brand, prodSize, prodColor, gender);

	 SET  x = x + 1; 

 END WHILE;
 SELECT * FROM products;
 
END //
DELIMITER ;
CALL insertProducts(20);


/*
Brown: 
*/
INSERT INTO carts (cartId, username, binaryNum) VALUES (1,'prbrown',0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (1, 1, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (1, 2, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (1, 3, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (1, 4, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (1, 5, 0);

/*
Dania:
*/
INSERT INTO carts (cartId, username, binaryNum) VALUES (2,'detienne20' ,0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 19, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 11, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 15, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 1, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 5, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 17, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (2, 16, 0);

/*
Nickan: No cart. Attempt to insert an item. 
*/
INSERT INTO carts (cartId, username, binaryNum) VALUES (3,'NHussaini' ,0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (3, 1, 0);
INSERT INTO purchase (cartId, itemId, binaryNum) VALUES (3, 5, 0);





