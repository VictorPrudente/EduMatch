package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Prova;
import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.ProvaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository repository;
    private final QuestaoService questaoService;
    private final UsuarioService usuarioService;
    private final ObjectMapper mapper;

    public ProvaStartDTO startTest(ProvaStartCreateDTO provaStartCreateDTO, Integer trilha, Integer dificuldade) throws Exception {

        UsuarioDTO usuarioDTO = usuarioService.listarPorId(provaStartCreateDTO.getIdUsuario());

        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);

        Prova prova = toEntity(provaStartCreateDTO);

        prova.setDataInicio(LocalDateTime.now());

        prova.setUsuario(usuario);

        prova.setQuestoes(gerarQuestoes(trilha, dificuldade));

        prova.shuffleOpcoes();
        
        return toDTO(repository.save(prova));

    }

    public ProvaStartDTO finishTest(ProvaStartCreateDTO provaStartCreateDTO){
        Prova prova = toEntity(provaStartCreateDTO);
        repository.save(prova);
        return null;
    }




    //Métodos adicionais

    private ProvaStartDTO toDTO(Prova prova){
        return mapper.convertValue(prova, ProvaStartDTO.class);
    }

    private Prova toEntity(Object o){
        return mapper.convertValue(o, Prova.class);
    }

    private List<Questao> gerarQuestoes(Integer trilha, Integer dificuldade) throws NaoEncontradoException, BancoDeDadosException {

        List<QuestaoDTO> questoes = new ArrayList<>(questaoService.find5ByTrilhaAndDificuldade(trilha, dificuldade));
        return questoes.stream()
                .map(questaoDTO -> mapper.convertValue(questaoDTO, Questao.class))
                .collect(Collectors.toList());
    }
}
