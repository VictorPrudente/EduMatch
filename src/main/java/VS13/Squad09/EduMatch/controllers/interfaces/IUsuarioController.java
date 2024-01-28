package VS13.Squad09.EduMatch.controllers.interfaces;

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

    @Operation(summary = "Mostra rank dos usuários", description = "Rankeia todos os usuários do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o rank de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping("/rankear")
    public ResponseEntity<List<UsuarioDTO>> rankearUsuarios() throws Exception;

    @Operation(summary = "Criar um usuario", description = "Cri usuario no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Criou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioCreateDTO usuario) throws Exception;

    @Operation(summary = "Atualizar um usuario", description = "Atualiza um usuario no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualizou  usuario"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable("idUsuario") @NotNull Integer id,
                                                @RequestBody @Valid UsuarioCreateDTO usuarioAtualizar) throws Exception;

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
}
