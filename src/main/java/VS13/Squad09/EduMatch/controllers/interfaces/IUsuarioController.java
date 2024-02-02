package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.request.LoginCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IUsuarioController {
    @Operation(summary = "Criar um usuario", description = "Criou um usuario no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Criou usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioCreateDTO usuario) throws Exception;


    @Operation(summary = "Listar todos os usuários", description = "Lista todos os usuários do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<UsuarioDTO>> listarTodos() throws BancoDeDadosException;


    @Operation(summary = "Lista um usuario", description = "Lista um usuario no banco pelo id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou um usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UsuarioDTO> listarPorId(@PathVariable("id") @NotNull Integer id) throws Exception;

    @Operation(summary = "Lista usuarios pelo status", description = "Lista usuarios no banco pelo status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou um usuario pelo e-mail"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/status/{stts}")
    ResponseEntity<List<UsuarioDTO>> listarPorStatus(@PathVariable @NotNull Integer stts) throws Exception;


    @Operation(summary = "Mostra rank dos usuários", description = "Rankeia todos os usuários do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o rank de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/rankear")
    ResponseEntity<List<UsuarioDTO>> rankearUsuarios() throws Exception;

    @Operation(summary = "Mostrar relatório de usuario", description = "Mostra relatório de usuario no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o relatório do usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario-completo/{idPesoa}")
    public ResponseEntity<List<UsuarioCompletoRelatorioDTO>> listarUsuarioCompletoRelatorio(@PathVariable("idUsuario") @NotNull Integer id);

    @Operation(summary = "Atualizar um usuario", description = "Atualiza um usuario no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualizou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody @Valid UsuarioCreateDTO usuarioAtualizar) throws Exception;


    @Operation(summary = "Deletar um usuario", description = "Deleta o usuario do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deletou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    ResponseEntity<Void> delete(@PathVariable("idUsuario") @NotNull Integer id) throws Exception;


    @Operation(summary = "Realizar um Login", description = "Realizar um login com e-mail e senha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário logado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/login")
    ResponseEntity<Boolean> login (@RequestBody LoginCreateDTO loginCreateDTO) throws Exception;
}
