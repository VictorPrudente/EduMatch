package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.request.LoginCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IUsuarioController {

    @Operation(summary = "Listar todos os usuários", description = "Lista todos os usuários do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() throws BancoDeDadosException;


    @Operation(summary = "Lista um usuario", description = "Lista um usuario no banco pelo id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou um usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listarPorId(@PathVariable("id") @NotNull Integer id) throws Exception;

    @Operation(summary = "Lista usuarios pelo status", description = "Lista usuarios no banco pelo status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou um usuario pelo e-mail"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/status/{stts}")
    public ResponseEntity<List<UsuarioDTO>> listarPorStatus(@PathVariable @NotNull Integer stts) throws Exception;

    @Operation(summary = "Atualizar um usuario", description = "Atualiza um usuario no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualizou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody @Valid UsuarioCreateDTO usuarioAtualizar,
                                                @PathVariable Integer idUsuario) throws Exception;

    @Operation(summary = "Deletar um usuario", description = "Deleta o usuario do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deletou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") @NotNull Integer id) throws Exception;

    @Operation(summary = "Mostrar relatório de usuario", description = "Mostra relatório de usuario no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o relatório do usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario-completo/{idUsuario}")
    public ResponseEntity<UsuarioCompletoRelatorioDTO> listarUsuarioCompletoRelatorio(@PathVariable("idUsuario") @NotNull Integer idUsuario);

    @Operation(summary = "Mostrar relatório de usuario com certificado", description = "Mostra relatório de usuario com certificado no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o relatório do usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario-com-certificado/{idUsuario}")
    public ResponseEntity<UsuarioECertificadoRelatorioDTO> listarUsuarioComCertificado(@PathVariable("idUsuario") @NotNull Integer idUsuario);

    @Operation(summary = "Mostrar lista de usuario paginado", description = "Mostra lista de usuario paginado no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista paginada de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario-paginado")
    public ResponseEntity<Page<Usuario>> listPaginada(@RequestParam(defaultValue = "0")
                                                      Integer paginaSolicitada,
                                                      @RequestParam(defaultValue = "10")
                                                      Integer tamanhoPagina);

}
