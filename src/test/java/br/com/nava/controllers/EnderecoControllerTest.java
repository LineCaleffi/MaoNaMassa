package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

import br.com.nava.dtos.EnderecoDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getAllTest() throws Exception{
		// armazena o objeto que fará o teste e colher o resultado
		ResultActions response = mockMvc.perform(get("/enderecos").contentType("application/json"));
		// pega o resultado no formato MvcResult
		MvcResult result = response.andReturn();
		// pega o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		EnderecoDTO[] lista = mapper.readValue(responseStr, EnderecoDTO[].class);
		assertThat(lista).isNotEmpty();
	} // funcionando
	
	@Test
	void getOneTest() throws Exception{
		ResultActions response = mockMvc.perform(get("/enderecos/1").contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		EnderecoDTO dto = mapper.readValue(responseStr, EnderecoDTO.class);
		
		// verifica se o ID é igual
		assertThat(dto.getId()).isEqualTo(1);
		//verifica se o resultado é igual
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	} // funcionando
	
	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setCep("01010010");
		endereco.setRua("Endereco para teste");
		endereco.setNumero(10);
		endereco.setCidade("Guarulhos");
		endereco.setEstado("SP");
		
		// envia a requisição para salvar
		ResultActions response = mockMvc.perform(post("/enderecos").content(mapper.writeValueAsString(endereco))
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		EnderecoDTO enderecoDTO = mapper.readValue(responseStr, EnderecoDTO.class);
		
		//Compara todos os valores e salva 
		assertThat(enderecoDTO.getId()).isPositive();
		assertThat(enderecoDTO.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoDTO.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoDTO.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoDTO.getCidade()).isEqualTo(endereco.getCidade());
		assertThat(enderecoDTO.getEstado()).isEqualTo(endereco.getEstado());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}// funcionando
	
	@Test
	void deleteTest() throws Exception {
		// para enviar a requisição
		ResultActions response = mockMvc.perform(delete("/enderecos/8").contentType("application/json"));

		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	} // funcionando
	
	@Test
	void updateTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setCep("11111111");
		endereco.setRua("Endereco Update");
		endereco.setNumero(10);
		endereco.setCidade("Guarulhos");
		
		// envia a requisição para salvar
		ResultActions response = mockMvc.perform(patch("/enderecos/7").content( mapper.writeValueAsString(endereco))
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		EnderecoDTO enderecoDTO = mapper.readValue(responseStr, EnderecoDTO.class);
		
		//Compara todos os valores e salva 
		assertThat(enderecoDTO.getId()).isPositive();
		assertThat(enderecoDTO.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoDTO.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoDTO.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoDTO.getCidade()).isEqualTo(endereco.getCidade());
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}// funcionando
}
