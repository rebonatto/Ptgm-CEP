alter table protegemed.capturaatual add periculosidade_corrente int default 1;
alter table protegemed.capturaatual add periculosidade_frequencia int default 1;
alter table protegemed.capturaatual add periculosidade_similaridade int default 1;
alter table protegemed.capturaatual add constraint fk_periculosidade_corrente foreign key(periculosidade_corrente) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual add constraint fk_periculosidade_frequencia foreign key(periculosidade_frequencia) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual add constraint fk_periculosidade_similaridade foreign key(periculosidade_similaridade) references protegemed.periculosidade_fuga(id);
alter table protegemed.capturaatual modify dataatual datetime(6);
alter table protegemed.capturaatual change codCaptura codCaptura int UNSIGNED NOT NULL;