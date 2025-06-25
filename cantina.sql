CREATE TABLE `itens_venda` (
  `id` int(11) NOT NULL,
  `id_venda` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `login` varchar(20) NOT NULL,
  `senha` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `vendedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `itens_venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_venda_idx` (`id_venda`),
  ADD KEY `fk_produto_idx` (`id_produto`);

ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vendedor` (`vendedor`);

ALTER TABLE `itens_venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `itens_venda`
  ADD CONSTRAINT `fk_itens_venda_produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`),
  ADD CONSTRAINT `fk_itens_venda_venda` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id`);

ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`vendedor`) REFERENCES `usuario` (`id`);
COMMIT;

INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Coca-Cola Lata 350ml', 5.00, 150);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Salgado Assado (Esfiha de Carne)', 7.50, 40);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Pão de Queijo (unidade)', 4.50, 80);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Água Mineral sem Gás 500ml', 3.00, 200);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Suco de Laranja Natural 300ml', 8.00, 25);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Chocolate Kit Kat 41,5g', 4.00, 120);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Salgado Frito (Coxinha de Frango)', 7.50, 45);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Chiclete Trident (unidade)', 2.50, 300);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Café Expresso', 5.50, 100);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Sanduíche Natural de Frango', 12.00, 15);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Bolo de Chocolate (fatia)', 9.00, 10);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Fini Tubes Morango (pacote)', 6.00, 90);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Biscoito Passatempo Recheado', 5.00, 60);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Guaraná Antarctica Lata 350ml', 5.00, 140);
INSERT INTO produto (nome, valor_unitario, quantidade_estoque) VALUES ('Paçoca (unidade)', 1.50, 250);
