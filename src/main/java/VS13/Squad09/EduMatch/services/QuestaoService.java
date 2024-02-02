package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.entities.Opcao;
import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.QuestaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final ObjectMapper objectMapper;

    @Value("${multiplicador.pontuacao}")
    private Integer multiplicador;


    public QuestaoDTO create(QuestaoCreateDTO questaoCreateDTO){

        Questao questao = toEntity(questaoCreateDTO);
        questao.setStatus(Status.ATIVO);
        questao.setPontos(pontuacao(questao));

        questaoRepository.save(questao);

        return toDTO(questao);
    }

    public QuestaoDTO findById(Integer id){
        return getById(id);
    }

    public List<QuestaoDTO> find5ByTrilhaAndDificuldade(Integer trilha, Integer dificuldade){
        return questaoRepository.get5ByTrilhaAndDificuldade(trilha, dificuldade).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public QuestaoDTO findByTrailAndDificulty(Integer trilha, Integer dificuldade) throws BancoDeDadosException, NaoEncontradoException {
        log.info("Buscando 1 questão");
        Questao questao = questaoRepository.findByTrilhaAndDificuldade(trilha, dificuldade);
        questao.shuffleOpcoes();
        return toDTO(questao);
    }

    public List<QuestaoDTO> findAllByTrailAndDificulty(Integer trilha, Integer dificuldade){
        return questaoRepository.findAllByTrilhaAndDificuldade(trilha, dificuldade).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public List<QuestaoDTO> findAllByTrail(Integer trilha){
        return questaoRepository.findAllByTrilhaOrderByDificuldade(trilha).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<QuestaoDTO> findAllByStatus(Integer status) {
        log.info("Buscando questões na service.");
        return questaoRepository.findAllByStatus(status).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public QuestaoDTO update(Integer id, QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException, NaoEncontradoException {

        //Seto a questão atualizada como inativa.
        Questao questao = toEntity(getById(id));
        questao.setStatus(Status.INATIVO);
        questaoRepository.save(questao);

        //crio uma nova questão, seto o status como ativa e salvo no banco de dados.
        Questao questaoAtualizada = toEntity(questaoCreateDTO);
        questaoAtualizada.setStatus(Status.ATIVO);
        questaoRepository.save(questaoAtualizada);
        return toDTO(questaoAtualizada);
    }

    public QuestaoDTO delete(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        log.info("Buscando questao na service.");
        //Procuro a questão no banco de dados. Seto como inativa e a atualizo.
        Questao questao = toEntity(getById(id));
        log.info("Encontrada questao na service.");
        log.info(questao.toString());
        questao.setStatus(Status.INATIVO);
        questao.setPergunta("apagada");
        log.info("setado status inativo na questao.");
        log.info(questao.toString());
        questaoRepository.save(questao);
        log.info("questao inativa no banco de dados.");
        return toDTO(questao);
    }



    //Métodos adicionais

    private QuestaoDTO getById(Integer id){
        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma questão encontrada com este id."));
        questao.shuffleOpcoes();
        return toDTO(questao);
    }

    private QuestaoDTO toDTO(Questao questao){
        return objectMapper.convertValue(questao, QuestaoDTO.class);
    }

    private Questao toEntity(Object object){
        return objectMapper.convertValue(object, Questao.class);
    }

    private Integer pontuacao(Questao questao){
        return (questao.getDificuldade().getNivel() * multiplicador) + multiplicador;
    }

}
