package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.mapper.ContatoMapper;
import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
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

        Contato contato = contatoRepository.listarPorDono(id);
        if(contato == null) {
            usuarioService.listarPorId(id);

            Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);

            ContatoDTO contatoDTO = contatoMapper.toDto(contatoRepository.adicionar(id, contatoEntity));

            return contatoDTO;
        }
        throw new RegraDeNegocioException("Usuário já possui um contato cadastrado.");
    }

    public ContatoDTO atualizar(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        listarPorId(id);

        Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);

        ContatoDTO contatoDTO = contatoMapper.toDto(contatoRepository.editar(id, contatoEntity));
        contatoDTO.setId(id);
        return contatoDTO;
    }

    public void deletar(Integer id) throws Exception {
        listarPorId(id);
        contatoRepository.remover(id);
    }

    public ContatoDTO listarPorId(Integer id) throws Exception {
        Contato contato = contatoRepository.listarPorId(id);
        if (contato == null){
            throw new NaoEncontradoException("Nenhum contato encontrado.");
        }
        return contatoMapper.toDto(contato);
    }


    public ContatoDTO listarPorUsuario(Integer idUsuario) throws Exception {
        Contato contato = contatoRepository.listarPorDono(idUsuario);
        if (contato != null){
            return contatoMapper.toDto(contatoRepository.listarPorDono(idUsuario));
        }
        throw new NaoEncontradoException("Nenhum contato cadastrado neste usuário.");
    }

}
