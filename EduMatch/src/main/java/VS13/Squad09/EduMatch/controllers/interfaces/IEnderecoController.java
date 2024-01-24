package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IEnderecoController {


    @Operation(summary = "Criar um Endereço", description = "Cadastra um endereço no banco de dados.")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @PostMapping("/usuario/{idUsuario}")
    ResponseEntity<EnderecoDTO> salvar(@PathVariable("idUsuario") Integer idUsuario,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;



    @Operation(summary = "Atualizar um Endereço", description = "Atualiza um endereço no banco de dados passando seu ip como parâmetro.")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado um endereço com este ID"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> atualizar(@PathVariable Integer idEndereco,
                                                 @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws exceptions.BancoDeDadosException;



    @Operation(summary = "Deletar um Endereço", description = "Remove um endereço no banco de dados passando seu ip como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornou a lista de pessoas"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado um endereço com este ID"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletar(@PathVariable Integer id) throws exceptions.BancoDeDadosException;



    @Operation(summary = "Listar todos os endereços pelo ID do seu dono", description = "Retorna uma lista de endereços do banco correspondentes ao ID do dono passado.")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de endereços"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado para o dono deste ID."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @GetMapping("/usuario/{id}")
    ResponseEntity<EnderecoDTO> listarPorDono(@PathVariable("id") Integer id) throws exceptions.BancoDeDadosException;
}
