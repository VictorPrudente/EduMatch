package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.mapper.EnderecoMapper;
import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Log;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import VS13.Squad09.EduMatch.entities.enums.TipoOperacao;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.EnderecoRepository;
import VS13.Squad09.EduMatch.repositories.LogRepository;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final LogRepository logRepository;
    private final String NOT_FOUND_MESSAGE = "ID do Contato não encontrado!";

    public EnderecoDTO salvar(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException, NaoEncontradoException {
        Usuario usuarioEntity = usuarioRepository.findById(idPessoa)
                .orElseThrow(() -> new NaoEncontradoException("O id do usuário informado não existe!"));

        if(usuarioEntity.getEndereco() != null){
            throw new RegraDeNegocioException("A pessoa informada já possui um endereço!");
        }

        Endereco enderecoEntity = enderecoMapper.toEntity(enderecoCreateDTO);
        enderecoEntity.setIdUsuario(usuarioEntity.getIdUsuario());
        enderecoEntity = enderecoRepository.save(enderecoEntity);

        usuarioService.usuarioComEndereco(usuarioEntity, enderecoEntity);

        Log log = new Log(TipoLog.ENDERECOS, TipoOperacao.SALVAR, "Endereco salvado", LocalDate.now());
        logRepository.save(log);

        return enderecoMapper.toDto(enderecoEntity);
    }

    public EnderecoDTO atualizar(Integer id, EnderecoCreateDTO enderecoAtualizarDTO) throws NaoEncontradoException {
        return enderecoMapper.toDto(
                enderecoRepository.save(
                        changeDados(returnEnderecoById(id), enderecoAtualizarDTO)
                ));
    }

    public void deletar(Integer id) throws NaoEncontradoException {
        Endereco endereco = returnEnderecoById(id);

        usuarioService.usuarioSemEndereco(endereco.getIdUsuario());

        enderecoRepository.delete(endereco);
    }

    public EnderecoDTO findById(Integer id) throws NaoEncontradoException {
        return enderecoMapper.toDto(returnEnderecoById(id));
    }

    private Endereco returnEnderecoById(Integer id) throws NaoEncontradoException {
        Log log = new Log(TipoLog.ENDERECOS, TipoOperacao.PROCURAR, "Procurado endereco pelo ID Endereco", LocalDate.now());
        logRepository.save(log);

        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(NOT_FOUND_MESSAGE));
    }

    public EnderecoDTO findEnderecoByIdUsuario(Integer idUsuario) throws NaoEncontradoException {
        return enderecoMapper.toDto(returnEnderecoByIdUsuario(idUsuario));
    }

    private Endereco returnEnderecoByIdUsuario(Integer idUsuario) throws NaoEncontradoException {
        return enderecoRepository.findEnderecoByIdUsuario(idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Usuário informado não possui contato!"));
    }

    private Endereco changeDados(Endereco enderecoExistente, EnderecoCreateDTO enderecoAtualizar){
        Optional.ofNullable(enderecoAtualizar.getTipoDeEndereco()).ifPresent(enderecoExistente::setTipoDeEndereco);
        Optional.ofNullable(enderecoAtualizar.getLogradouro()).ifPresent(enderecoExistente::setLogradouro);
        Optional.ofNullable(enderecoAtualizar.getNumero()).ifPresent(enderecoExistente::setNumero);
        Optional.ofNullable(enderecoAtualizar.getComplemento()).ifPresent(enderecoExistente::setComplemento);
        Optional.ofNullable(enderecoAtualizar.getCep()).ifPresent(enderecoExistente::setCep);
        Optional.ofNullable(enderecoAtualizar.getCidade()).ifPresent(enderecoExistente::setCidade);
        Optional.ofNullable(enderecoAtualizar.getEstado()).ifPresent(enderecoExistente::setEstado);
        Optional.ofNullable(enderecoAtualizar.getPais()).ifPresent(enderecoExistente::setPais);

        return enderecoExistente;
    }
}