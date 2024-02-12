package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
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


    public InsigniaDTO atualizar(Integer idInsignia, InsigniaCreateDTO insigniaCreateDTO) throws NaoEncontradoException {

        Insignia insignia = getInsignia(idInsignia);

        Insignia insigniaAtualizada = toEntity(insigniaCreateDTO);
        insigniaAtualizada.setId(insignia.getId());

        insigniaRepository.save(insigniaAtualizada);

        return toDTO(insigniaAtualizada);
    }

    public List<InsigniaDTO> listarPorUsuario(Integer idUsuario, Integer idInsignia) throws NaoEncontradoException {
        List<InsigniaDTO> insignias = new ArrayList<>();

        if (idInsignia != null) {
            insignias.add(insigniaRepository.findOneByOwner(idUsuario, idInsignia));
        } else {
            insignias = insigniaRepository.findAllByOwner(idUsuario);
        }
        if (insignias.contains(null)) {
            throw new NaoEncontradoException("Nenhum insignia encontrada para este usuário");
        }
            return insignias;
    }


    public void addUsuario(Usuario usuario, String tag) throws Exception {

        Insignia insignia = acharPorTag(tag);

        insignia.getUsuarios().add(usuario);

        insigniaRepository.save(insignia);
    }

    public List<InsigniaDTO> listarInsignias(Integer idInsingia) {
        List<InsigniaDTO> insignias = new ArrayList<>();
        if (idInsingia != null){
            insignias.add(insigniaRepository.findInsignia(idInsingia));
        } else {
            insignias.addAll(insigniaRepository.findInsignias());
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

    private Insignia getInsignia(Integer idInsignia) throws NaoEncontradoException {
        return insigniaRepository.findById(idInsignia)
                .orElseThrow(() -> new NaoEncontradoException("Nenhuma insignia com o ID fornecido fora encontrada."));
    }

    private Insignia acharPorTag(String descricao){
        return insigniaRepository.findByTagIgnoreCase(descricao);
    }

}
