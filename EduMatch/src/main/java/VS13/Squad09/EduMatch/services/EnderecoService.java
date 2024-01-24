package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.interfaces.Service;
import VS13.Squad09.EduMatch.repositories.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EnderecoService implements Service<Endereco> {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public EnderecoDTO salvar(Integer id, EnderecoCreateDTO endereco) {
        try {
            enderecoRepository.adicionar(id, objectMapper.convertValue(endereco, Endereco.class));
            return objectMapper.convertValue(endereco, EnderecoDTO.class);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        try {
            enderecoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EnderecoDTO atualizar(Integer id, EnderecoCreateDTO endereco) {
        try {
            enderecoRepository.editar(id, objectMapper.convertValue(endereco, Endereco.class));
            return objectMapper.convertValue(endereco, EnderecoDTO.class);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EnderecoDTO> listarTodos() {
        try {
            return enderecoRepository.listar().stream()
                    .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                    .collect(Collectors.toList());
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Endereco listarPorDono(int id) {
        //este id vem da entidade que estiver chamando o seu endereco. Por exemplo, usuario.getId();
        try {
            return enderecoRepository.listarPorDono(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Endereco não encontrado.");
        return null;
    }

}