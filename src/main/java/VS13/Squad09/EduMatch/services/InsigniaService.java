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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public InsigniaDTO criar(InsigniaCreateDTO insignia) throws Exception {
        log.debug("Criando Insignia...");
        Insignia insigniaEntity = objectMapper.convertValue(insignia, Insignia.class);

        insigniaRepository.save(insigniaEntity);

        return objectMapper.convertValue(insigniaEntity, InsigniaDTO.class);
    }

    public void deletar(int id) throws Exception {
        //não será deletada uma insignia do usuário.
    }

    public InsigniaDTO listarPorUsuario(Integer usuarioId) throws Exception {
        Usuario usuario = objectMapper.convertValue(usuarioService.listarPorId(usuarioId), Usuario.class);

        List<Insignia> insignias = insigniaRepository.findAll();

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

    public InsigniaDTO findBadge(String descricao){
        return toDTO(insigniaRepository.findByTagIgnoreCase(descricao));
    }


    public InsigniaDTO addUsuario(Integer idUsuaraio, Integer idInsignia) throws Exception {

        UsuarioDTO usuarioDTO = usuarioService.listarPorId(idUsuaraio);
        Usuario usuario = objectMapper.convertValue(usuarioDTO, Usuario.class);

        Insignia insignia = insigniaRepository.findById(idInsignia).get();
        InsigniaDTO insigniaDTO = objectMapper.convertValue(insignia, InsigniaDTO.class);

        insignia.getUsuarios().add(usuario);
        return objectMapper.convertValue(insigniaRepository.save(insignia), InsigniaDTO.class);
    }

    //MÉTODOS ADICIONAIS

    private InsigniaDTO toDTO(Insignia insignia){
        return objectMapper.convertValue(insignia, InsigniaDTO.class);
    }

}
