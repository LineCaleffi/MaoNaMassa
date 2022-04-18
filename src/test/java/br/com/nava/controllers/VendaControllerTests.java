package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.VendaDTO;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VendaControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test // getAll Vendas
	void getAllTest() throws Exception {
		// faz o MOCK no get
		ResultActions response = mockMvc.perform(get("/vendas").contentType("application/json"));
		
		MvcResult result = response.andReturn();
		
		// guarda o resultado
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		VendaDTO[] lista = mapper.readValue(responseStr, VendaDTO[].class);
		assertThat(lista).isNotEmpty();
	} // funcionando
	
	@Test
	void getOneTest() throws Exception {
		// armazena o objeto que fará o test e colher o resultado
		ResultActions response = mockMvc.perform(get("/vendas/5").contentType("application/json"));
		
		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		// pegando o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
					
		ObjectMapper mapper = new ObjectMapper();
		
		VendaDTO venda = mapper.readValue(responseStr, VendaDTO.class);
		
		assertThat(venda.getId()).isEqualTo(5);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	} // funcionando
	
	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	 //List <ProdutoDTO> listaDTO = produtoRepository.findAll();
		VendaDTO venda = new VendaDTO();		
		venda.setValorTotal((float) 900.0);		
		ResultActions response = mockMvc.perform(post("/vendas").content(mapper.writeValueAsString(venda)).contentType("application/json"));	
			
		MvcResult result = response.andReturn();
			
		String responseStr = result.getResponse().getContentAsString();

		VendaDTO vendaSalvo = mapper.readValue(responseStr, VendaDTO.class);
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(venda.getValorTotal());				
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	} // funcionando 
	
	// método deletar para teste
	@Test
	void deleteTest() throws Exception {
		// para enviar a requisição
		ResultActions response = mockMvc.perform(delete("/vendas/14").contentType("application/json"));

		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	} // funcionando
	
}
