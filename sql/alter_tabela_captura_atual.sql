/*
	Novas alterações com a adição do Drools ao Protegemed
*/
alter table protegemed.capturaatual add periculosidade_corrente int default 1;
alter table protegemed.capturaatual add periculosidade_frequencia int default 1;
alter table protegemed.capturaatual add periculosidade_similaridade int default 1;
alter table protegemed.capturaatual add constraint fk_periculosidade_corrente foreign key(periculosidade_corrente) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual add constraint fk_periculosidade_frequencia foreign key(periculosidade_frequencia) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual add constraint fk_periculosidade_similaridade foreign key(periculosidade_similaridade) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual modify dataatual datetime(6);
alter table protegemed.capturaatual change codCaptura codCaptura int(11) NOT NULL;

/*
	Alterações já existentes na base de dados atual
*/
ALTER TABLE `capturaatual`
  ADD CONSTRAINT `fk_capturaatual_1` FOREIGN KEY (`codEvento`) REFERENCES `eventos` (`codEvento`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_2` FOREIGN KEY (`codTipoOnda`) REFERENCES `tipoonda` (`codTipoOnda`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_3` FOREIGN KEY (`codEquip`) REFERENCES `equipamento` (`codEquip`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_4` FOREIGN KEY (`codTomada`) REFERENCES `tomada` (`codTomada`) ON UPDATE NO ACTION;
  
/*
	Restrições para a tabela `harmatual`
*/
ALTER TABLE `harmatual`
  ADD CONSTRAINT `fk_harmatual_1` FOREIGN KEY (`codCaptura`) REFERENCES `capturaatual` (`codCaptura`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*
	Restrições para a tabela `harmpadrao`
*/
ALTER TABLE `harmpadrao`
  ADD CONSTRAINT `fk_harmpadrao_1` FOREIGN KEY (`codOndaPadrao`) REFERENCES `ondapadrao` (`codOndaPadrao`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*
	Restrições para a tabela `ondapadrao`
*/
ALTER TABLE `ondapadrao`
  ADD CONSTRAINT `codEquip` FOREIGN KEY (`codEquip`) REFERENCES `equipamento` (`codEquip`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `codTomada` FOREIGN KEY (`codTomada`) REFERENCES `tomada` (`codTomada`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ondapadrao_1` FOREIGN KEY (`codTipoOnda`) REFERENCES `tipoonda` (`codTipoOnda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ondapadrao_2` FOREIGN KEY (`codTipoPadrao`) REFERENCES `tipopadrao` (`codTipoPadrao`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*
	Restrições para a tabela `usosala`
*/
ALTER TABLE `usosala`
  ADD CONSTRAINT `codProced` FOREIGN KEY (`codProced`) REFERENCES `procedimento` (`codProced`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `codResp` FOREIGN KEY (`codResp`) REFERENCES `responsavel` (`codResp`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `codSala` FOREIGN KEY (`codSala`) REFERENCES `salacirurgia` (`codSala`) ON DELETE CASCADE ON UPDATE NO ACTION;