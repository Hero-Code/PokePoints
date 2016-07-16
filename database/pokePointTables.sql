CREATE TABLE pokemon(
	id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	imageUrl TEXT,
	PRIMARY KEY(id)
);

--INSERT INTO pokemon VALUES(1,'bulbasaur','http://url/img.jpg')

CREATE TABLE pokemon_type(
	pokemonId INTEGER NOT NULL,
	type VARCHAR(50) NOT NULL,
	FOREIGN KEY(pokemonId) REFERENCES pokemon(id),
	PRIMARY KEY(pokemonId, type)
);

--INSERT INTO pokemon_type VALUES(1,'grass')
--INSERT INTO pokemon_type VALUES(1,'poison')

CREATE TABLE pokepoint(
	id SERIAL,
	point TEXT NOT NULL,
	pokemonId INT NOT NULL,
	pokemonCp INT NOT NULL,
	userLevel INT,
	FOREIGN KEY(pokemonId) REFERENCES pokemon(id),
	PRIMARY KEY(id)
);

--INSERT INTO pokepoint(point,pokemonId,pokemonCp,userLevel) 
--	VALUES('POINT(-6.8909, 38.5637)',1,65,3)

--INSERT INTO pokepoint(point,pokemonId,pokemonCp,userLevel) 
--	VALUES('POINT(-7.909, 36.5637)',1,78,4)