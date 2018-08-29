SET SQL_SAFE_UPDATES = 0;
CREATE USER 'protegemed'@'localhost' IDENTIFIED BY 'senha.123'
GRANT ALL PRIVILEGES ON * . * TO 'protegemed'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE protegemed.versao(
	id integer not null auto_increment ,
	data datetime not null,
	id_versao varchar(20) not null,
    descricao varchar(100),
	constraint pk_versao_frequencia primary key(id)
);

CREATE TABLE protegemed.escala_corrente(
    valor decimal(6, 5) not null,
	id_tipo integer not null,
    id_versao integer not null,
    constraint pk_correntes primary key(valor, id_versao),
    constraint fk_versao foreign key(id_versao) references versao(id),
    constraint fk_tipo_corrente foreign key(id_tipo) references periculosidade_fuga(id)
);

CREATE TABLE protegemed.escala_frequencia(
	id_tipo integer not null,
    id_versao integer not null,
    valor decimal(6, 5) not null,
    frequencia int not null,
    constraint pk_frequencias primary key(valor, frequencia, id_versao),
    constraint fk_versao_frequencia foreign key(id_versao) references versao(id),
    constraint fk_tipo_frequencia foreign key(id_tipo) references periculosidade_fuga(id)
);

CREATE TABLE protegemed.escala_similaridade(
	id_tipo integer not null,
    id_versao integer not null,
    valor_min decimal(6, 5) not null,
    valor_max decimal(6, 5) not null,
    constraint pk_periculosidade primary key(valor_min, valor_max, id_versao),
    constraint fk_versao_periculosidade foreign key(id_versao) references versao(id),
    constraint fk_tipo_periculosidade foreign key(id_tipo) references periculosidade_fuga(id)
);

create table protegemed.periculosidade_fuga(
    id integer not null auto_increment,
    tipo char(1) not null,
    descricao varchar(50) not null,
    constraint pk_periculosidade_fuga primary key(id)
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


