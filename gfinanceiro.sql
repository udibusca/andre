-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Máquina: 127.0.0.1
-- Data de Criação: 18-Dez-2016 às 20:49
-- Versão do servidor: 5.6.14
-- versão do PHP: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `gfinanceiro`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `dtaCadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`idcategoria`, `nome`, `dtaCadastro`, `ativo`) VALUES
(1, 'Contas', '2016-12-13 19:10:15', 1),
(2, 'Vendas', '2016-12-13 19:10:19', 1),
(3, 'Pe&ccedilas', '2016-12-13 19:10:44', 1),
(4, 'Retiradas', '2016-12-13 19:12:28', 1),
(5, 'Salarios', '2016-12-13 19:12:43', 1),
(6, 'FlÃ¡viana', '2016-12-14 02:07:28', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `data_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` int(11) NOT NULL DEFAULT '1',
  `saldo` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nome`, `cpf`, `data_cadastro`, `ativo`, `saldo`) VALUES
(1, 'André Castro Alves', '08276821669', '2016-12-13 20:28:51', 1, '522.04'),
(2, 'Bruno Benicio Do Nascimento', '01886706611', '2016-12-13 20:28:51', 1, '86.04'),
(3, 'Carlos Eduardo Henry Gomes', '56253022270', '2016-12-18 18:55:43', 1, '415.79'),
(4, 'Joana Isabella Alves', '93214424726', '2016-12-18 18:56:56', 1, '800.91');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `idfornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `ativo` int(11) NOT NULL DEFAULT '1',
  `data_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idfornecedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`idfornecedor`, `nome`, `cnpj`, `ativo`, `data_cadastro`) VALUES
(1, 'AMBEv', '07526557000100', 1, '2016-12-13 18:53:38'),
(2, 'Harman do brasil', '88315379000170', 1, '2016-12-13 20:30:44'),
(3, 'Pietra e Lara Marcenaria Ltda', '72719536000172', 1, '2016-12-18 18:52:24'),
(4, 'Antonella e Arthur Entulhos Ltda', '45220804000134', 1, '2016-12-18 18:52:59'),
(5, 'Nicolas e Juan Adega Ltda', '24134346000140', 1, '2016-12-18 18:53:15'),
(6, 'Isaac e Rafael Telecom ME', '88625575000140', 1, '2016-12-18 18:54:57');

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimentacao`
--

CREATE TABLE IF NOT EXISTS `movimentacao` (
  `idmovimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `data_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valor` decimal(7,2) DEFAULT NULL,
  `tipo_movimentacao` int(11) NOT NULL COMMENT 'Exemplo: 1 para entrada 2 para saída',
  `dsc` text,
  `fornecedor_idfornecedor` int(11) DEFAULT NULL,
  `cliente_idcliente` int(11) DEFAULT NULL,
  `categoria_idcategoria` int(11) NOT NULL,
  `ativo` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idmovimentacao`),
  KEY `fk_movimentacao_fornecedor_idx` (`fornecedor_idfornecedor`),
  KEY `fk_movimentacao_cliente1_idx` (`cliente_idcliente`),
  KEY `fk_movimentacao_categoria1_idx` (`categoria_idcategoria`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `movimentacao`
--

INSERT INTO `movimentacao` (`idmovimentacao`, `data_cadastro`, `valor`, `tipo_movimentacao`, `dsc`, `fornecedor_idfornecedor`, `cliente_idcliente`, `categoria_idcategoria`, `ativo`) VALUES
(1, '2016-12-13 20:29:25', '45.99', 1, 'Venda de fone', NULL, 1, 2, 1),
(2, '2016-12-13 20:31:10', '-10.50', 2, 'Compra de fone para venda', 2, NULL, 3, 1),
(3, '2016-12-18 18:58:08', '100.00', 1, 'Venda de produto', NULL, 3, 2, 1),
(4, '2016-12-18 18:59:13', '-300.45', 2, 'Compras estoque', 4, NULL, 3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `ativo` varchar(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nome`, `email`, `usuario`, `senha`, `ativo`) VALUES
(17, 'Andre Luiz', 'alpmidia@gmail.com', 'andre asd', '123', '1'),
(18, 'Natasha', 'natasha@gmail.com', 'nat', '222', '1'),
(19, 'Bruno', 'bbn_d7@hotmail.com.br', 'bbn_d7@hotmail.com.br', '123', '1');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `movimentacao`
--
ALTER TABLE `movimentacao`
  ADD CONSTRAINT `fk_movimentacao_categoria1` FOREIGN KEY (`categoria_idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_movimentacao_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_movimentacao_fornecedor` FOREIGN KEY (`fornecedor_idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
