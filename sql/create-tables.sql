SET SQL_SAFE_UPDATES = 0;
CREATE USER 'protegemed'@'localhost' IDENTIFIED BY 'senha.123'
GRANT ALL PRIVILEGES ON * . * TO 'protegemed'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE versao_frequencia(
	id integer not null auto_increment ,
	data datetime not null,
	descricao varchar(100),
	constraint pk_versao_frequencia primary key(id)
);

CREATE TABLE frequencia_normal(
	valor decimal(6, 5) not null,
    id_versao integer not null,
    constraint pk_frequencia_normal primary key(valor, id_versao),
    constraint fk_versao_frequencia_n foreign key(id_versao) references versao_frequencia(id)
);

CREATE TABLE frequencia_atencao(
	valor decimal(6, 5) not null,
    id_versao integer not null,
    constraint pk_frequencia_atencao primary key(valor, id_versao),
    constraint fk_versao_frequencia_a foreign key(id_versao) references versao_frequencia(id)
);

CREATE TABLE frequencia_perigo(
	valor decimal(6, 5) not null,
    id_versao integer not null,
    constraint pk_frequencia_intervencao primary key(valor, id_versao),
    constraint fk_versao_frequencia_p foreign key(id_versao) references versao_frequencia(id)
);

CREATE TABLE RESULTADO_SIMILARIDADE(
    ID INTEGER NOT NULL AUTO_INCREMENT,
    CD_EQUIPAMENTO_1 INTEGER NOT NULL,
    CD_EQUIPAMENTO_2 INTEGER NOT NULL,
    VALUE_1 DECIMAL(4,2) NOT NULL,
    VALUE_2 DECIMAL(4,2) NOT NULL,
    VALUE_3 DECIMAL(4,2) NOT NULL,
    CONSTRAINT PK_RESULT_SIMILARIDADE PRIMARY KEY(ID),
    CONSTRAINT FK_EQUIPAMENTO_1 FOREIGN KEY(CD_EQUIPAMENTO_1) REFERENCES EQUIPAMENTO(CODEQUIP),
    CONSTRAINT FK_EQUIPAMENTO_2 FOREIGN KEY(CD_EQUIPAMENTO_2) REFERENCES EQUIPAMENTO(CODEQUIP)
);

CREATE TABLE IF NOT EXISTS `equipamento` (
  `codEquip` int(11) NOT NULL AUTO_INCREMENT,
  `codMarca` int(11) NOT NULL,
  `codModelo` int(11) NOT NULL,
  `codTipo` int(11) NOT NULL,
  `rfid` varchar(45) NOT NULL,
  `codPatrimonio` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `dataUltimaFalha` date DEFAULT NULL,
  `dataUltimaManutencao` date DEFAULT NULL,
  `tempoUso` int(11) DEFAULT NULL,
  PRIMARY KEY (`codEquip`),
  KEY `fk_equipamento_1_idx` (`codTipo`),
  KEY `fk_equipamento_2_idx` (`codMarca`),
  KEY `fk_equipamento_3_idx` (`codModelo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

insert into versao_frequencia(data, descricao) values(now(), 'Versão Rebonatto');
insert into versao_frequencia(data, descricao) values(now(), 'Versão Clayton');

insert into frequencia_normal(valor, id_versao) values(0.060, 1);
insert into frequencia_normal(valor, id_versao) values(0.061, 1);
insert into frequencia_normal(valor, id_versao) values(0.064, 1);
insert into frequencia_normal(valor, id_versao) values(0.068, 1);
insert into frequencia_normal(valor, id_versao) values(0.073, 1);
insert into frequencia_normal(valor, id_versao) values(0.078, 1);
insert into frequencia_normal(valor, id_versao) values(0.083, 1);
insert into frequencia_normal(valor, id_versao) values(0.088, 1);
insert into frequencia_normal(valor, id_versao) values(0.093, 1);
insert into frequencia_normal(valor, id_versao) values(0.099, 1);
insert into frequencia_normal(valor, id_versao) values(0.103, 1);
insert into frequencia_normal(valor, id_versao) values(0.107, 1);

insert into frequencia_atencao(valor, id_versao) values(0.1000, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1028, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1057, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1104, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1158, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1248, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1260, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1311, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1359, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1400, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1446, 1);
insert into frequencia_atencao(valor, id_versao) values(0.1486, 1);

insert into frequencia_perigo(valor, id_versao) values(0.5000, 1);
insert into frequencia_perigo(valor, id_versao) values(0.7919, 1);
insert into frequencia_perigo(valor, id_versao) values(1.0781, 1);
insert into frequencia_perigo(valor, id_versao) values(1.5691, 1);
insert into frequencia_perigo(valor, id_versao) values(2.3368, 1);
insert into frequencia_perigo(valor, id_versao) values(2.6370, 1);
insert into frequencia_perigo(valor, id_versao) values(2.9746, 1);
insert into frequencia_perigo(valor, id_versao) values(3.3344, 1);
insert into frequencia_perigo(valor, id_versao) values(3.7110, 1);
insert into frequencia_perigo(valor, id_versao) values(4.0876, 1);
insert into frequencia_perigo(valor, id_versao) values(4.4893, 1);
insert into frequencia_perigo(valor, id_versao) values(4.9049, 1);
