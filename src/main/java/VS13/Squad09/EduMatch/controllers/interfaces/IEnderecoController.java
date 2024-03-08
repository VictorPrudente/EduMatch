package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IEnderecoController {

    @Operation(summary = "Criar um Endereço", description = "Cadastra um endereço no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Criou um endereço"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Endereço não criado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Endereço não criado.")})
    @PostMapping("/usuario/{idUsuario}")
    ResponseEntity<EnderecoDTO> salvar(@PathVariable("idUsuario") Integer idUsuario,
                                       @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;


    @Operation(summary = "Atualizar um Endereço", description = "Atualiza um endereço no banco de dados passando seu ID como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Endereço atualizado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Endereço não atualizado."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado um endereço com este ID. Endereço não atualizado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Endereço não atualizado.")})
    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> atualizar(@PathVariable("idEndereco") Integer idEndereco,
                                          @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;


    @Operation(summary = "Deletar um Endereço", description = "Remove um endereço no banco de dados passando seu ID como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Endereço deletado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Endereço não deletado."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado um endereço com este ID. Endereço não deletado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Endereço não deletado.")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable Integer id) throws Exception;


    @Operation(summary = "Listar todos os endereços pelo ID do seu dono", description = "Retorna uma lista de endereços do banco correspondentes ao ID daquele dono.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado para o dono deste ID."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @GetMapping("/usuario/{id}")
    ResponseEntity<EnderecoDTO> listarPorDono(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Retornar o endereço pelo ID", description = "Retorna um endereço do banco correspondente ao ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado para esse ID."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @GetMapping("/{id}")
    ResponseEntity<EnderecoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception;
}
