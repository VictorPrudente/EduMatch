package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.InsigniaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public InsigniaDTO criar(InsigniaCreateDTO insigniaCreateDTO) throws Exception {

        Insignia insignia = toEntity(insigniaCreateDTO);

        insignia.setStatus(Status.ATIVO);

        insigniaRepository.save(insignia);

        return toDTO(insignia);
    }




    public List<InsigniaDTO> listarPorUsuario(Integer usuarioId, Integer idInsignia) throws Exception {

        Usuario usuario = objectMapper.convertValue(usuarioService.listarPorId(usuarioId), Usuario.class);

        if (usuario.getInsignias().isEmpty()) {
            throw new NaoEncontradoException("Nenhum insignia encontrada para este usuário");
        }

        List<InsigniaDTO> insignias = usuario.getInsignias().stream().map(this::toDTO).toList();

        if (idInsignia != null) {
            return insignias.stream()
                    .filter(insigniaDTO -> insigniaDTO.getId().equals(idInsignia))
                    .findFirst()
                    .map(Collections::singletonList)
                    .orElseThrow(() -> new NaoEncontradoException("Nenhuma insígnia com este ID foi encontrada."));
        }
            return insignias;
    }


    public InsigniaDTO addUsuario(Usuario usuario, String tag) throws Exception {
        Insignia insignia = acharPorTag(tag);
        InsigniaDTO insigniaDetailedDTO = objectMapper.convertValue(insignia, InsigniaDTO.class);

        insignia.getUsuarios().add(usuario);
        return objectMapper.convertValue(insigniaRepository.save(insignia), InsigniaDTO.class);
    }

    public List<InsigniaDTO> listarInsignias(Integer idInsingia) {
        List<InsigniaDTO> insignias = new ArrayList<>();
        if (idInsingia == null){
            insignias.addAll(insigniaRepository.findInsignias());
        } else {
            insignias.add(toDTO(insigniaRepository.findInsignia(idInsingia)));
        }
        return insignias;
    }

    //MÉTODOS ADICIONAIS

    private InsigniaDTO toDTO(Object o){
        return objectMapper.convertValue(o, InsigniaDTO.class);
    }

    private Insignia toEntity(Object o){
        return objectMapper.convertValue(o, Insignia.class);
    }

    private Insignia acharPorTag(String descricao){
        return insigniaRepository.findByTagIgnoreCase(descricao);
    }

}
