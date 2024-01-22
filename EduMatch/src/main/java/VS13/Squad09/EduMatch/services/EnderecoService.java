package VS13.Squad9.EduMatch.services;

import VS13.Squad9.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad9.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad9.EduMatch.entities.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import lombok.RequiredArgsConstructor;
import repository.EnderecoRepository;
import java.util.List;

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
    public void deletar(int id) {
        try {
            enderecoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EnderecoDTO atualizar(int id, EnderecoCreateDTO endereco) {
        try {
            enderecoRepository.editar(id, objectMapper.convertValue(endereco, Endereco.class));
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Endereco não atualizado.");
        return false;
    }


    @Override
    public void listarTodos() {
        try {
            List<Endereco> listar = enderecoRepository.listar();
            listar.forEach(System.out::println);
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