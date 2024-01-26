package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.repositories.QuestaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final ObjectMapper objectMapper;


    public QuestaoDTO create(QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
            Questao questao = questaoRepository.create(objectMapper.convertValue(questaoCreateDTO, Questao.class));
            return objectMapper.convertValue(questao, QuestaoDTO.class);
        }

    public QuestaoDTO update(Integer id, QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
            Questao questao = objectMapper.convertValue(questaoCreateDTO, Questao.class);
            return objectMapper.convertValue(questaoRepository.update(id, questao), QuestaoDTO.class);
    }


    public String deletar(int id) throws BancoDeDadosException {
        return questaoRepository.delete(id);
    }


    public QuestaoDTO listByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException {
            Questao questao = questaoRepository.listByTrailAndDificulty(trilha, dificuldade);
            return objectMapper.convertValue(questao, QuestaoDTO.class);
    }
}