package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICertificadoController {

    @Operation(summary = "Listar certificados", description = "Retorna lista de todos os certificados do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de certificados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<CertificadoDTO>> listarTodos() throws Exception;


    @Operation(summary = "Listar último certificado", description = "Retorna lista do último certificado do usuário do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou último certificado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario/{usuarioId}/ultimo")
    ResponseEntity<CertificadoDTO> listarUltimo(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception;


    @Operation(summary = "Listar certificados por usuário", description = "Retorna lista de todos os certificados do banco de dados por usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou todos os certificados do usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o usuário fornecido"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario/{usuarioId}")
    ResponseEntity<List<CertificadoDTO>> listarPorUsuario(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception;


    @Operation(summary = "Criar um Certificado", description = "Cadastra um certificado no banco de dados vinculado a um usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criou um certificado"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @PostMapping
    ResponseEntity<CertificadoDTO> criar(@PathVariable Integer idUsuario, @Valid @RequestBody CertificadoCreateDTO certificado) throws Exception;


    @Operation(summary = "Deletar um Certificado", description = "Deleta um certificado no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deletou um certificado"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o certificado com o id fornecido"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletar(@NotNull @PathVariable("id") Integer id) throws Exception;
}
