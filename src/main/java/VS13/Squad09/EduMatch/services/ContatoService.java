package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.mapper.ContatoMapper;
import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ContatoRepository;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;
    private final ContatoMapper contatoMapper;
    private final UsuarioRepository usuarioRepository;
    private final String NOT_FOUND_MESSAGE = "ID do Contato não encontrado!";

    public ContatoDTO salvar(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws Exception {
        Usuario usuarioEntity = usuarioRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("O id da pessoa informado é inválido!"));

        if(usuarioEntity.getContato() != null){
            throw new RegraDeNegocioException("A pessoa informada já possui um contato!");
        }

        Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);
        contatoEntity.setUsuario(usuarioEntity);
        contatoEntity = contatoRepository.save(contatoEntity);

        usuarioService.usuarioComContato(usuarioEntity, contatoEntity);

        return contatoMapper.toDto(contatoEntity);
    }

    public ContatoDTO atualizar(Integer id, ContatoCreateDTO contatoAtualizarDTO) throws Exception {
        return contatoMapper.toDto(
                contatoRepository.save(
                        changeDados(returnContatoById(id), contatoAtualizarDTO)
                ));
    }

    public void deletar(Integer id) throws Exception {
        Contato contato = returnContatoById(id);

        usuarioService.usuarioSemContato(contato.getUsuario());

        contatoRepository.delete(contato);
    }

    public ContatoDTO findByIdContato(Integer id) throws Exception{
        return contatoMapper.toDto(obterContatoPorId(id));
    }

    public ContatoDTO findContatoByUsuarioId(Integer idUsuario) throws Exception{
        if (returnContatoByIdUsuario(idUsuario) == null){
            throw new NaoEncontradoException("O contato é nulo!");
        }
        return contatoMapper.toDto(returnContatoByIdUsuario(idUsuario));
    }

    private Contato obterContatoPorId(Integer id) throws Exception {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Id informado não pertence a um contato válido!"));
    }

    private Contato returnContatoByIdUsuario(Integer idUsuario) throws NaoEncontradoException {
        return contatoRepository.findAll()
                .stream()
                .filter(contato -> contato.getUsuario().getIdUsuario().equals(idUsuario))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Usuário informado não possui endereço!"));
    }

    private Contato returnContatoById(Integer id) throws RegraDeNegocioException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }

    private Contato changeDados(Contato contatoExistente, ContatoCreateDTO contatoAtualizar){
        Optional.ofNullable(contatoAtualizar.getTipo()).ifPresent(contatoExistente::setTipo);
        Optional.ofNullable(contatoAtualizar.getTelefone()).ifPresent(contatoExistente::setTelefone);
        Optional.ofNullable(contatoAtualizar.getDescricao()).ifPresent(contatoExistente::setDescricao);

        return contatoExistente;
    }
}
