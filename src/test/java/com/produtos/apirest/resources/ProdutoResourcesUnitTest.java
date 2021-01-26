package com.produtos.apirest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.produtos.apirest.model.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoResourcesUnitTest {

	@InjectMocks
	private ProdutoResource produtoResource;

	@Mock
	private ProdutoRepository produtoRepository;

	@Mock
	private Produto produto;

	@Test
	void deveRetornarProdutos() {
		when(produtoRepository.findAll()).thenReturn(List.of(produto));
		assertNotNull(produtoResource.listaProdutos());
	}

	@Test
	void deveRetornarProdutoUnico() {
		when(produtoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produto));
		assertNotNull(produtoResource.listaProdutoUnico(1L));
	}

	@Test
	void deveSalvarProduto() {
		when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(produto);
		assertEquals(produto, produtoResource.salvaProduto(new Produto()));
	}
	
	@Test
	void deveDeletarUmProduto() {
		doNothing().when(produtoRepository).delete(Mockito.any(Produto.class));
		produtoResource.deletaProduto(new Produto());
		verify(produtoRepository, timeout(1)).delete(Mockito.any(Produto.class));
	}
	
	@Test
	void deveAtualizarProduto() {
		when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(produto);
		assertEquals(produto, produtoResource.atualizarProduto(new Produto()));
	}
}
