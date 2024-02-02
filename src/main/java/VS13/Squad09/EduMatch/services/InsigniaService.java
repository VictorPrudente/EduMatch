package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.repositories.InsigniaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public InsigniaDTO criar(Integer idUsuario, InsigniaCreateDTO insignia) throws Exception {
        log.debug("Criando Insignia...");
        UsuarioDTO usuarioDTO = usuarioService.listarPorId(idUsuario);
        Insignia insigniaEntity = objectMapper.convertValue(insignia, Insignia.class);
        insigniaEntity.setDataEmitida(LocalDateTime.now());
        Usuario usuario = objectMapper.convertValue(usuarioDTO, Usuario.class);
        insigniaEntity.setUsuario(usuario);

        insigniaRepository.save(insigniaEntity);
        InsigniaDTO insigniaDTO = objectMapper.convertValue(insigniaEntity, InsigniaDTO.class);

        return insigniaDTO;
    }

    public void deletar(int id) throws Exception {
        //não será deletada uma insignia do usuário.
    }

    public InsigniaDTO listarPorUsuario(Integer usuarioId) throws Exception {
        Usuario usuario = objectMapper.convertValue(usuarioService.listarPorId(usuarioId), Usuario.class);

        List<Insignia> insignias = insigniaRepository.findAllByUsuario(usuario);

        if (insignias.isEmpty()) {
            throw new Exception("Nenhum insignia encontrado para o usuário com ID: " + usuarioId);
        }

        return objectMapper.convertValue(insignias, InsigniaDTO.class);
    }

    public List<InsigniaDTO> listarTodas() throws Exception {
        return insigniaRepository.findAll().stream()
                .map(insignia -> objectMapper.convertValue(insignia, InsigniaDTO.class))
                .collect(Collectors.toList());
    }
}
