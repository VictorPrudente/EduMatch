package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public EnderecoDTO salvar(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws Exception {

        Endereco enderecoExistente = enderecoRepository.listarPorDono(id);
        if (enderecoExistente == null) {
            usuarioService.listarPorId(id);

            Endereco endereco = enderecoRepository.adicionar(id, objectMapper.convertValue(enderecoCreateDTO, Endereco.class));

            return objectMapper.convertValue(endereco, EnderecoDTO.class);
        } throw new RegraDeNegocioException("Usuário já possui um endereço cadastrado.");
    }

    public EnderecoDTO listarPorId(Integer id) throws NaoEncontradoException, BancoDeDadosException {
        Endereco endereco = enderecoRepository.listarPorId(id);
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public String deletar(Integer id) throws BancoDeDadosException, NaoEncontradoException {
        return enderecoRepository.remover(id);
    }

    public EnderecoDTO atualizar(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException, NaoEncontradoException {
        listarPorId(id);
        Endereco endereco = enderecoRepository.editar(id, objectMapper.convertValue(enderecoCreateDTO, Endereco.class));
        endereco.setId(id);
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public List<EnderecoDTO> listarTodos() throws BancoDeDadosException {
        return enderecoRepository.listar().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listarPorDono(Integer idUsuario) throws BancoDeDadosException, NaoEncontradoException {
        Endereco endereco = enderecoRepository.listarPorDono(idUsuario);
        if (endereco == null){
            throw new NaoEncontradoException("Nenhum endereço cadastrado.");
        }
        return objectMapper.convertValue(endereco,
                EnderecoDTO.class);
    }

}