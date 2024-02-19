package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.mapper.ContatoMapper;
import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ContatoRepository;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoMapper contatoMapper;
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final String NOT_FOUND_MESSAGE = "ID do Contato não encontrado!";

    public ContatoDTO salvar(Integer idUsuario, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException, NaoEncontradoException {
        Usuario usuarioEntity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("O id do usuário informado não existe!"));

        if(usuarioEntity.getContato() != null){
            throw new RegraDeNegocioException("A pessoa informada já possui um contato!");
        }

        Contato contatoEntity = contatoMapper.toEntity(contatoCreateDTO);
        contatoEntity.setIdUsuario(usuarioEntity.getIdUsuario());
        contatoEntity = contatoRepository.save(contatoEntity);

        usuarioService.usuarioComContato(usuarioEntity, contatoEntity);

        return contatoMapper.toDto(contatoEntity);
    }

    public ContatoDTO atualizar(Integer id, ContatoCreateDTO contatoAtualizarDTO) throws NaoEncontradoException {
        return contatoMapper.toDto(
                contatoRepository.save(
                        changeDados(returnContatoById(id), contatoAtualizarDTO)
                ));
    }

    public void deletar(Integer id) throws NaoEncontradoException {
        Contato contato = returnContatoById(id);

        usuarioService.usuarioSemContato(contato.getIdUsuario());

        contatoRepository.delete(contato);
    }

    public ContatoDTO findById(Integer id) throws NaoEncontradoException{
        return contatoMapper.toDto(returnContatoById(id));
    }

    private Contato returnContatoById(Integer id) throws NaoEncontradoException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(NOT_FOUND_MESSAGE));
    }

    public ContatoDTO findContatoByIdUsuario(Integer idUsuario) throws NaoEncontradoException {
        return contatoMapper.toDto(returnContatoByIdUsuario(idUsuario));
    }

    private Contato returnContatoByIdUsuario(Integer idUsuario) throws NaoEncontradoException {
        return contatoRepository.findContatoByIdUsuario(idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Usuário informado não possui contato!"));
    }

    private Contato changeDados(Contato contatoExistente, ContatoCreateDTO contatoAtualizar){
        Optional.ofNullable(contatoAtualizar.getTipo()).ifPresent(contatoExistente::setTipo);
        Optional.ofNullable(contatoAtualizar.getTelefone()).ifPresent(contatoExistente::setTelefone);
        Optional.ofNullable(contatoAtualizar.getDescricao()).ifPresent(contatoExistente::setDescricao);

        return contatoExistente;
    }
}
