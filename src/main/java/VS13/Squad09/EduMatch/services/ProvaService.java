package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaFinishCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.insignia.response.InsigniaDetailedDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaFinishDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.*;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ProvaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository repository;
    private final QuestaoService questaoService;
    private final UsuarioService usuarioService;
    private final InsigniaService insigniaService;
    private final ObjectMapper mapper;


    @Value("${tempo.minimo}")
    private Integer tempo;

    @Value("${aprovacao}")
    private Integer aprovacao;

    public ProvaStartDTO startTest(ProvaStartCreateDTO provaStart) throws Exception {

        UsuarioDTO usuarioDTO = usuarioService.listarPorId(provaStart.getIdUsuario());

        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);

        Prova prova = toEntity(provaStart);

        prova.setDataInicio(LocalDateTime.now());

        int duracao = tempo * provaStart.getDificuldade().ordinal();

        prova.setTempoLimite(duracao + tempo) ;
        prova.setUsuario(usuario);
        prova.setStatus(Status.ATIVO);

        List<Questao> questoes = gerarQuestoes(provaStart.getTrilha().ordinal(),
                                               provaStart.getDificuldade().ordinal());
        //ORDENAR POR ID
        prova.setQuestoes(questoes);
        prova.setTotalQuestoes(questoes.size());

        prova.shuffleOpcoes();
        repository.save(prova);

        return toDTO(prova);

    }

    public ProvaFinishDTO finishTest(Integer idProva, ProvaFinishCreateDTO provaFinishCreateDTO) throws Exception {

        Prova prova = getById(idProva);
        prova.setDataFinal(LocalDateTime.now());

        validarProva(prova);

        Integer pontuacao = 0;
        Integer acertos = 0;

        List<Questao> questoes = new ArrayList<>(prova.getQuestoes());

        List<Resposta> respostas = new ArrayList<>(provaFinishCreateDTO.getRespostas());

        for (Questao questao : questoes) {
            for (Resposta resposta : respostas) {
                if(resposta.getResposta().equals(questao.getOpcaoCerta())){
                    pontuacao += questao.getPontos();
                    acertos ++;
                    break;
                }
            }
        }


        prova.getRespostas().addAll(respostas);
        prova.setPontos(pontuacao);
        prova.setTotalAcertos(acertos);
        prova.setStatus(Status.INATIVO);
        repository.save(prova);

        Usuario usuario = prova.getUsuario();
        usuario.pontuar(pontuacao+200);


        Questao questao = prova.getQuestoes().get(0);
        if (acertos * 100 / questoes.size() >= aprovacao){
            String tag = questao.getTrilha().name() + "_" + questao.getDificuldade().name();
            InsigniaDetailedDTO insigniaDetailedDTO = insigniaService.acharPorTag(tag);
            InsigniaDetailedDTO insignia = mapper.convertValue(insigniaDetailedDTO, InsigniaDetailedDTO.class);
        }
        UsuarioCreateDTO usuarioCreateDTO = mapper.convertValue(usuario, UsuarioCreateDTO.class);
        usuarioService.atualizar(usuario.getIdUsuario(), usuarioCreateDTO);
        ProvaFinishDTO provaFinishDTO = new ProvaFinishDTO();
        BeanUtils.copyProperties(prova, provaFinishDTO);
        return provaFinishDTO;
    }


    //Métodos adicionais

    private Prova getById(Integer id) throws NaoEncontradoException {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Nenhuma prova encontrada com este id."));
    }

    private boolean validarProva(Prova prova) throws RegraDeNegocioException {
        if(prova.getStatus().equals(Status.INATIVO)){
            throw new RegraDeNegocioException("Prova já finalizada.");
        }

        Duration duracao = Duration.between(prova.getDataInicio(), prova.getDataFinal());
        long tempoUsado = Math.abs(duracao.getSeconds());
        if(tempoUsado > prova.getTempoLimite()){
            prova.setStatus(Status.INATIVO);
            repository.save(prova);
            throw new RegraDeNegocioException("Tempo esgotado.");
        }
        return true;
    }

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
