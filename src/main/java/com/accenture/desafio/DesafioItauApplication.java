package com.accenture.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.desafio.modelo.Carrinho;
import com.accenture.desafio.modelo.CategoriaEnum;
import com.accenture.desafio.modelo.Cliente;
import com.accenture.desafio.modelo.Frete;
import com.accenture.desafio.modelo.Produto;
import com.accenture.desafio.servico.CarrinhoService;
import com.accenture.desafio.servico.DescontoServico;
import com.accenture.desafio.servico.ImpostoServico;

@SpringBootApplication
public class DesafioItauApplication {
	private static final Logger LOGGER = Logger.getLogger(DesafioItauApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(DesafioItauApplication.class, args);

		DescontoServico descontoServico = new DescontoServico();
		ImpostoServico impostoServico = new ImpostoServico();
		CarrinhoService carrinhoService = new CarrinhoService();
		List<Produto> produtos = new ArrayList<Produto>();

		Cliente cliente = new Cliente.ClienteBuilder().nome("Maiculino de Andrade").build();

		Frete frete = new Frete.FreteBuilder().cepOrigem("30690-770").cepDestino("55620-000").build();

		Produto produto = new Produto.ProdutoBuilder()
				.descricao("Trançado do Rei Careca")
				.cupom("DESIT")
				.promocao(true)
				.categoriaEnum(CategoriaEnum.E_READER)
				.valor(20)
				.build();
		produtos.add(produto);

		Produto produto2 = new Produto.ProdutoBuilder()
				.descricao("Poeira em auto mar")
				.valor(20)
				.build();
		produtos.add(produto2);

		LOGGER.log(Level.INFO, "Calculando o desconto");
		produtos = descontoServico.calcularDesconto(produtos);

		LOGGER.log(Level.INFO, "Calculando o imposto");
		produtos = impostoServico.calcularImposto(produtos);

		Carrinho carrinho = new Carrinho.CarrinhoBuilder()
				.cliente(cliente)
				.produtos(produtos)
				.frete(frete).build();

		LOGGER.log(Level.INFO, "Calculando o frete");
		frete = carrinhoService.calcularFrete(carrinho);
		
		carrinho = carrinhoService.calcularValorFinalCompra(carrinho);
		
		LOGGER.log(Level.INFO, "Carrinho " + carrinho.toString());
	}

}
