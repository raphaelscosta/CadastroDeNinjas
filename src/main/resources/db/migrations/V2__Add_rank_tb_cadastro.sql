--Migrations para adicionar a coluna de RANK na tabela de cadastro
ALTER TABLE TB_CADASTRO
ADD COLUMN rank VARCHAR(255);