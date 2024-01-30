package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.response.ClassificacaoDTO;
import VS13.Squad09.EduMatch.repositories.ClassificacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ClassificacaoService {

    private final ClassificacaoRepository classificacaoRepository;
    private final ObjectMapper objectMapper;



    public List<ClassificacaoDTO> listarTodos() throws Exception {
        log.debug("Listando Classificacaos...");
        return classificacaoRepository.listar().stream().map(classificacao ->
                        objectMapper.convertValue(classificacao, ClassificacaoDTO.class))
                .collect(Collectors.toList());
    }

}
