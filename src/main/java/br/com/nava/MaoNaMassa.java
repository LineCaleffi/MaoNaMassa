package br.com.nava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nava.repositories.ProfessorRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.services.BDService;

@SpringBootApplication
public class MaoNaMassa implements CommandLineRunner{

//	//o sistema define quando usar a variavel professorRepository
//	@Autowired
//	private ProfessorRepository professorRepository;
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//	
//	@Autowired
//	private BDService bdService;

	public static void main(String[] args) {
		SpringApplication.run(MaoNaMassa.class, args);
	}


	// -- para ler os dados que foram inseridos no banco
	@Override
	public void run(String...args) throws Exception{
//		bdService.inserirVendas();
		
//		// SELECT * DO PROFESSOR;
//		List<ProfessorEntity> listaProfessor = professorRepository.findAll();
//
//		// mostra todos os professores no console			
//		// Para retornar os dados de todos os professores do banco de dados
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			System.out.println(listaProfessor.get(i));
//		}
//		
//		// para todos retornar os usuários do banco de dados
//		List<UsuarioEntity> listaUsuario = usuarioRepository.findAll();
//				
//		for (int i = 0; i < listaUsuario.size(); i++) {
//			System.out.println(listaUsuario.get(i));
//		}
//		
//		// - Para inserir novos dados no banco de dados
//		UsuarioEntity usuario = new UsuarioEntity();
//		usuario.setEmail("fabrizzio@grandeporte.com.br");
//		usuario.setNome("Professor Fabrizzio");
//		
//		usuarioRepository.save(usuario); 
	}
}
