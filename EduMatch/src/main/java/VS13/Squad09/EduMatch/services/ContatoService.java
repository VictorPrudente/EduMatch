package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper mapper;

    public ContatoDTO salvar(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        usuarioService.listarPorId(id);

        Contato contatoEntity = mapper.convertValue(contatoCreateDTO, Contato.class);

        ContatoDTO contatoDTO = mapper.convertValue(contatoRepository.adicionar(id, contatoEntity), ContatoDTO.class);

        return contatoDTO;
    }

    public ContatoDTO atualizar(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        listarPorId(id);

        Contato contatoEntity = mapper.convertValue(contatoCreateDTO, Contato.class);

        ContatoDTO contatoDTO = mapper.convertValue(contatoRepository.editar(id, contatoEntity), ContatoDTO.class);

        return contatoDTO;
    }

    public void deletar(Integer id) throws Exception {
        listarPorId(id);
        contatoRepository.remover(id);
    }

    public Contato listarPorId(Integer id) throws Exception {
        return contatoRepository.listar().stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado!"));
    }
}
