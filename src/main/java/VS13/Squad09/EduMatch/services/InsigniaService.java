package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.repositories.InsigniaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final ObjectMapper objectMapper;

    public InsigniaDTO criar(InsigniaCreateDTO insignia) throws Exception {
        log.debug("Criando Insignia...");

        Insignia insigniaEntity = objectMapper.convertValue(insignia, Insignia.class);
        insigniaEntity.setDataEmitida(LocalDateTime.now());
        insigniaEntity = insigniaRepository.adicionar(insigniaEntity);

        InsigniaDTO insigniaDTO = objectMapper.convertValue(insigniaEntity, InsigniaDTO.class);

        return insigniaDTO;
    }

    public String deletar(int id) throws Exception {
        log.debug("Deletando insignia...");

        return insigniaRepository.remover(id);
    }

    public List<InsigniaDTO> listarTodos() throws Exception {
        log.debug("Listando Insignias...");
        return insigniaRepository.listar().stream().map(insignia ->
                        objectMapper.convertValue(insignia, InsigniaDTO.class))
                .collect(Collectors.toList());
    }

    public InsigniaDTO listarUltimo(Integer usuarioId) throws Exception {
        log.debug("Listando último Certficado...");
        Insignia insignia = insigniaRepository.listarUltimo(usuarioId);

        if (insignia == null) {
            throw new Exception("Nenhum insignia encontrado para o usuário com ID: " + usuarioId);
        }

        InsigniaDTO insigniaDTO = objectMapper.convertValue(insignia, InsigniaDTO.class);
        return insigniaDTO;
    }


    public InsigniaDTO listarPorUsuario(Integer usuarioId) throws Exception {
        List<Insignia> insignias = insigniaRepository.listarPorUsuario(usuarioId);

        if (insignias.isEmpty()) {
            throw new Exception("Nenhum insignia encontrado para o usuário com ID: " + usuarioId);
        }

        InsigniaDTO insigniaDTO = objectMapper.convertValue(insignias, InsigniaDTO.class);
        return insigniaDTO;
    }
}
