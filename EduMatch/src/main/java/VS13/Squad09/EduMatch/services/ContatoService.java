package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.mapper.ContatoMapper;
import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;
    private final ContatoMapper contatoMapper;

    public ContatoDTO salvar(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        usuarioService.listarPorId(id);

        Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);

        ContatoDTO contatoDTO = contatoMapper.toDto(contatoRepository.adicionar(id, contatoEntity));

        return contatoDTO;
    }

    public ContatoDTO atualizar(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        listarPorId(id);

        Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);

        ContatoDTO contatoDTO = contatoMapper.toDto(contatoRepository.editar(id, contatoEntity));

        return contatoDTO;
    }

    public void deletar(Integer id) throws Exception {
        listarPorId(id);
        contatoRepository.remover(id);
    }

    private Contato listarPorId(Integer id) throws Exception {
        return contatoRepository.listar().stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado!"));
    }

    public List<ContatoDTO> listarPorUsuario(Integer idUsuario) throws Exception {
        return contatoMapper.toDto(contatoRepository.listarPorDono(idUsuario));
    }
}
