package VS13.Squad09.EduMatch.services;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaFinishCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaFinishDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.entities.*;
import VS13.Squad09.EduMatch.entities.enums.Resultado;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ProvaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository repository;
    private final QuestaoService questaoService;
    private final UsuarioService usuarioService;
    private final InsigniaService insigniaService;
    private final ObjectMapper mapper;


    @Value("${tempo.minimo}") //segundos
    private Integer tempo;

    @Value("${aprovacao}") //porcentagem
    private Integer aprovacao;

    public ProvaStartDTO startTest(ProvaStartCreateDTO provaStart) throws Exception {

        UsuarioDTO usuarioDTO = usuarioService.listarPorId(provaStart.getIdUsuario());

        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);

        Prova prova = toEntity(provaStart);

        prova.setDataInicio(LocalDateTime.now());

        int duracao = tempo * provaStart.getDificuldade().ordinal();

        prova.setTempoLimite(duracao + tempo);
        prova.setUsuario(usuario);
        prova.setStatus(Status.ATIVO);

        List<Questao> questoes = buscarQuestoes(provaStart.getTrilha().ordinal(),
                                               provaStart.getDificuldade().ordinal());
        //ORDENAR POR ID
        prova.setQuestoes(questoes);
        prova.setTotalQuestoes(questoes.size());

        repository.save(prova);

        return toDTO(prova);

    }

    public ProvaFinishDTO finishTest(Integer idProva, ProvaFinishCreateDTO provaFinishCreateDTO) throws Exception {

        Prova prova = getById(idProva);
        prova.setDataFinal(LocalDateTime.now());

        validarProva(prova);

        Integer pontuacao = 0;
        int acertos = 0;

        List<Questao> questoes = new ArrayList<>(prova.getQuestoes());

        List<Resposta> respostas = new ArrayList<>(provaFinishCreateDTO.getRespostas());
        List<Resposta> respostasFinal = respostas;
        for (Questao questao : questoes) {
            for (Resposta resposta : respostas) {
                if(resposta.getResposta().equals(questao.getOpcaoCerta())){
                    pontuacao += questao.getPontos();
                    respostas.remove(resposta);
                    acertos ++;
                    break;
                }
            }
        }

        prova.getRespostas().addAll(respostasFinal);
        prova.setPontos(pontuacao);
        prova.setTotalAcertos(acertos);
        prova.setStatus(Status.INATIVO);

        double nota = Double.parseDouble(new DecimalFormat("#.##")
                .format(acertos * 100.0 / questoes.size()));

        prova.setNota(nota);

        Usuario usuario = prova.getUsuario();
        usuario.pontuar(pontuacao+200);


        Questao questao = prova.getQuestoes().get(0);

        if (nota >= aprovacao){
            prova.setResultado(Resultado.APROVADO);
            String tag = questao.getTrilha().name() + "_" + questao.getDificuldade().name();
            insigniaService.addUsuario(usuario, tag);
        } else {
            prova.setResultado(Resultado.REPROVADO);
        }

        repository.save(prova);
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

    private void validarProva(Prova prova) throws RegraDeNegocioException {
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
    }

    //MÉTODOS ADICIONAIS

    private ProvaStartDTO toDTO(Prova prova){
        return mapper.convertValue(prova, ProvaStartDTO.class);
    }

    private Prova toEntity(Object o){
        return mapper.convertValue(o, Prova.class);
    }

    private List<Questao> buscarQuestoes(Integer trilha, Integer dificuldade) {
        return questaoService.prepareQuestoes(trilha, dificuldade);
    }
}
