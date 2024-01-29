package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.QuestaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final ObjectMapper objectMapper;


    public QuestaoDTO create(QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
        Questao questao = objectMapper.convertValue(questaoCreateDTO, Questao.class);
        questao.setStatus(Status.ATIVO);
        questaoRepository.create(questao);
        return objectMapper.convertValue(questao, QuestaoDTO.class);
    }

    public QuestaoDTO findById(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        Questao questao = questaoRepository.findById(id);
        return objectMapper.convertValue(questao, QuestaoDTO.class);
    }


    public QuestaoDTO findByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException, NaoEncontradoException {
        log.info("Buscando 1 questão");
        Questao questao = questaoRepository.findByTrailAndDificulty(trilha, dificuldade);
        log.info("Retornando uma questão " + questao.getPontos());
        return objectMapper.convertValue(questao, QuestaoDTO.class);
    }

    public List<QuestaoDTO> findAllByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException {
        return questaoRepository.findAllByTrailAndDificulty(trilha, dificuldade).stream()
                .map(questao -> objectMapper.convertValue(questao, QuestaoDTO.class))
                .collect(Collectors.toList());
    }


    public List<QuestaoDTO> findAllByTrail(Integer trilha) throws BancoDeDadosException {
        return questaoRepository.findAllByTrail(trilha).stream()
                .map(questao -> objectMapper.convertValue(questao, QuestaoDTO.class))
                .collect(Collectors.toList());
    }

    public List<QuestaoDTO> findAllActive() throws BancoDeDadosException {
        log.info("Buscando questões na service.");
        return questaoRepository.findAll().stream()
                .map(questao -> objectMapper.convertValue(questao, QuestaoDTO.class))
                .collect(Collectors.toList());
    }

    public QuestaoDTO update(Integer id, QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException, NaoEncontradoException {

        //Seto a questão atualizada como inativa.
        Questao questao = questaoRepository.findById(id);
        questao.setStatus(Status.INATIVO);
        questaoRepository.update(questao);

        //crio uma nova questão, seto o status como ativa e salvo no banco de dados.
        Questao questaoAtualizada = objectMapper.convertValue(questaoCreateDTO, Questao.class);
        questaoAtualizada.setStatus(Status.ATIVO);
        questaoRepository.create(questaoAtualizada);
        return objectMapper.convertValue(questaoAtualizada, QuestaoDTO.class);
    }

    public QuestaoDTO delete(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        log.info("Buscando questao na service.");
        //Procuro a questão no banco de dados. Seto como inativa e a atualizo.
        Questao questao = questaoRepository.findById(id);
        log.info("Encontrada questao na service.");
        log.info(questao.toString());
        questao.setStatus(Status.INATIVO);
        questao.setPergunta("apagada");
        log.info("setado status inativo na questao.");
        log.info(questao.toString());
        questaoRepository.update(questao);
        log.info("questao inativa no banco de dados.");
        return objectMapper.convertValue(questao, QuestaoDTO.class);
    }
}
