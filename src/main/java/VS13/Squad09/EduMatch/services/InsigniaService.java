package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.insignia.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.insignia.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.insignia.response.InsigniaDetailedDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.repositories.InsigniaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public InsigniaDetailedDTO criar(InsigniaCreateDTO insigniaCreateDTO) throws Exception {

        insigniaRepository.save(toEntity(insigniaCreateDTO));

        return toDetailDTO(insigniaCreateDTO);
    }

    public List<Object> listarInsignias(Integer idInsingia) {
        List<Object> insignias = new ArrayList<>();
        if (idInsingia == null){
            insignias.addAll(insigniaRepository.findAll().stream()
                    .map(this::toDTO).toList());
        } else {
            insignias.add(toDetailDTO(insigniaRepository.findById(idInsingia)));
        }
        return insignias;
    }


    public List<Object> listarPorUsuario(Integer usuarioId, Integer idInsignia) throws Exception {

        Usuario usuario = objectMapper.convertValue(usuarioService.listarPorId(usuarioId), Usuario.class);
        List<Object> insignias = new ArrayList<>();

        if (usuario.getInsignias().isEmpty()) {
            throw new Exception("Nenhum insignia encontrada para o usuário com ID: " + usuarioId);
        }
        if(idInsignia == null){
            insignias.addAll(usuario.getInsignias().stream()
                    .map(this::toDTO).toList());
        } else {
            insignias.add(usuario.getInsignias().stream()
                    .filter(insignia -> insignia.getId().equals(idInsignia)));
        }
        return insignias;
    }

    public InsigniaDetailedDTO acharPorTag(String descricao){
        return toDetailDTO(insigniaRepository.findByTagIgnoreCase(descricao));
    }


    public InsigniaDetailedDTO addUsuario(Integer idUsuaraio, Integer idInsignia) throws Exception {

        UsuarioDTO usuarioDTO = usuarioService.listarPorId(idUsuaraio);
        Usuario usuario = objectMapper.convertValue(usuarioDTO, Usuario.class);

        Insignia insignia = insigniaRepository.findById(idInsignia).get();
        InsigniaDetailedDTO insigniaDetailedDTO = objectMapper.convertValue(insignia, InsigniaDetailedDTO.class);

        insignia.getUsuarios().add(usuario);
        return objectMapper.convertValue(insigniaRepository.save(insignia), InsigniaDetailedDTO.class);
    }

    //MÉTODOS ADICIONAIS

    private InsigniaDetailedDTO toDetailDTO(Object o){
        return objectMapper.convertValue(o, InsigniaDetailedDTO.class);
    }


    private InsigniaDTO toDTO(Object o){
        return objectMapper.convertValue(o, InsigniaDTO.class);
    }

    private Insignia toEntity(Object o){
        return objectMapper.convertValue(o, Insignia.class);
    }

}
