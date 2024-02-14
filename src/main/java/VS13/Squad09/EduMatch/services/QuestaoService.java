package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.QuestaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

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

    public QuestaoDTO update(Integer id, QuestaoCreateDTO questaoCreateDTO) throws NaoEncontradoException {

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

    public QuestaoDTO findById(Integer id) throws NaoEncontradoException {
        return getById(id);
    }

    public Page<QuestaoDTO> questoesPage(Integer trilha, Integer dificuldade, Integer status, Pageable pageable)  {


        if (trilha != null && dificuldade != null) {
            return questaoRepository.pageTrilhaDificuldade(trilha, dificuldade, status, pageable)
                    .map(this::toDTO);
        } else if (trilha != null) {
            return questaoRepository.pageTrilha(trilha, status, pageable)
                    .map(this::toDTO);
        } else if (status != null) {
            return questaoRepository.pageStatus(status, pageable)
                    .map(this::toDTO);
        }else {
            return questaoRepository.pageAll(pageable)
                    .map(this::toDTO);
        }
    }

    public List<Questao> prepareQuestoes(Integer trilha, Integer dificuldade){
        List<Questao> questoes = questaoRepository.prepareQuestoes(trilha, dificuldade);
        for(Questao questao : questoes) {
            questao.shuffleOpcoes();
        }
        return questoes;
    }

    public QuestaoDTO delete(Integer id) throws NaoEncontradoException {
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

    private QuestaoDTO getById(Integer id) throws NaoEncontradoException {
        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Nenhuma questão encontrada com este id."));
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
