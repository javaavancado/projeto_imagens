CREATE SCHEMA `projeto_imagens` DEFAULT CHARACTER SET latin1 ;


CREATE TABLE `projeto_imagens`.`imagens` (
  `id` INT NOT NULL auto_increment,
  `produto` VARCHAR(500) NULL,
  `fornecedor` VARCHAR(500) NULL,
  `miniatura` VARCHAR(200) NULL,
  `urlimagem`  longtext ,
  `urlminiimg` longtext ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;  