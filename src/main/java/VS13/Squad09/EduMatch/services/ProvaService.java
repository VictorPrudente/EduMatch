package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.ProvaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ProvaDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository repository;
    private final QuestaoService questaoService;
    private final ObjectMapper mapper;

    public ProvaDTO create(ProvaCreateDTO provaCreateDTO, Integer trilha, Integer dificuldade) throws NaoEncontradoException, BancoDeDadosException {
        Prova prova = toEntity(provaCreateDTO);
        prova.setDataInicio(LocalDateTime.now());
        prova.setQuestoes(gerarQuestoes(trilha, dificuldade));
        return toDTO(prova);
    }




    //Métodos adicionais

    private ProvaDTO toDTO(Prova prova){
        return mapper.convertValue(prova, ProvaDTO.class);
    }

    private Prova toEntity(Object o){
        return mapper.convertValue(o, Prova.class);
    }

    private List<Questao> gerarQuestoes(Integer trilha, Integer dificuldade) throws NaoEncontradoException, BancoDeDadosException {
        List<QuestaoDTO> questoes = new ArrayList<>();
        for (int i = 0; i<10; i++){
            questoes.add(questaoService.findByTrailAndDificulty(trilha, dificuldade));
        }
        return questoes.stream()
                .map(questao -> mapper.convertValue(questao, Questao.class))
                .collect(Collectors.toList());
    }
}
