package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.repositories.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO salvar(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        Endereco endereco = enderecoRepository.adicionar(id, objectMapper.convertValue(enderecoCreateDTO, Endereco.class));
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public void deletar(Integer id) throws BancoDeDadosException {
        enderecoRepository.remover(id);
    }

    public EnderecoDTO atualizar(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        Endereco endereco = enderecoRepository.editar(id, objectMapper.convertValue(enderecoCreateDTO, Endereco.class));
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public List<EnderecoDTO> listarTodos() throws BancoDeDadosException {
        return enderecoRepository.listar().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listarPorDono(Integer idUsuario) throws BancoDeDadosException {
        return objectMapper.convertValue(
                enderecoRepository.listarPorDono(idUsuario),
                EnderecoDTO.class);
    }

}