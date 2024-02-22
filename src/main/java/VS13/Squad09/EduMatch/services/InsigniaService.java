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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final ObjectMapper objectMapper;

    public InsigniaDTO create(InsigniaCreateDTO insigniaCreateDTO) {

        Insignia insignia = toEntity(insigniaCreateDTO);

        insignia.setStatus(Status.ATIVO);

        insigniaRepository.save(insignia);

        return toDTO(insignia);
    }


    public InsigniaDTO update(Integer idInsignia, InsigniaCreateDTO insigniaCreateDTO) throws NaoEncontradoException {

        Insignia insignia = getInsignia(idInsignia);

        Insignia insigniaAtualizada = toEntity(insigniaCreateDTO);
        insigniaAtualizada.setId(insignia.getId());

        insigniaRepository.save(insigniaAtualizada);

        return toDTO(insigniaAtualizada);
    }

    public List<InsigniaDTO> findByUser(Integer idUsuario, Integer idInsignia) {
        if (idInsignia != null) {
            return insigniaRepository.findOneByOwner(idUsuario, idInsignia);
        } else {
            return insigniaRepository.findAllByOwner(idUsuario);
        }
    }


    public void addUsuario(Usuario usuario, String tag) throws Exception {

        Insignia insignia = acharPorTag(tag);

        insignia.getUsuarios().add(usuario);

        insigniaRepository.save(insignia);
    }

    public List<InsigniaDTO> findInsignias(Integer idInsingia) {
        if (idInsingia != null){
            return insigniaRepository.findInsignia(idInsingia);
        }
            return insigniaRepository.findInsignias();
    }

    //MÉTODOS ADICIONAIS
    private InsigniaDTO toDTO(Object o){
        return objectMapper.convertValue(o, InsigniaDTO.class);
    }

    private Insignia toEntity(Object o){
        return objectMapper.convertValue(o, Insignia.class);
    }

    private Insignia getInsignia(Integer idInsignia) throws NaoEncontradoException {
        return insigniaRepository.findById(idInsignia)
                .orElseThrow(() -> new NaoEncontradoException("Nenhuma insignia com o ID fornecido fora encontrada."));
    }

    private Insignia acharPorTag(String descricao){
        return insigniaRepository.findByTagIgnoreCase(descricao);
    }

}
