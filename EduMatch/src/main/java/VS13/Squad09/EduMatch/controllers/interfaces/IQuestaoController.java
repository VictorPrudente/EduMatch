package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IQuestaoController {


    @Operation(summary = "Criar uma Questão", description = "Cadastra uma questão no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Criou uma questão."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não criada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não criada.")})
    @PostMapping
    ResponseEntity<QuestaoDTO> create(@RequestBody @Valid QuestaoCreateDTO questaoCreateDTO)
            throws BancoDeDadosException;



    @Operation(summary = "Atualizar uma Questão", description = "Atualiza uma questão no banco de dados passando seu id como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Questão atualizada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não atualizada."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com este ID. Questão não atualizada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não atualizada.")})
    @PutMapping("{id}")
    ResponseEntity<QuestaoDTO> update(@PathVariable Integer id,
                                      @RequestBody @Valid QuestaoCreateDTO questaoCreateDTO)
            throws BancoDeDadosException;


    @Operation(summary = "Deletar uma questão", description = "Remove uma questão no banco de dados passando seu id como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Questão deletada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não deletada."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com este ID. Questão não deletada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não deletada.")})
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id)
            throws BancoDeDadosException;



    @Operation(summary = "Listar uma questão pela sua dificuldade e sua trilha.", description = "Lista uma questão passando como parâmetro sua trilha e o nível da dificuldade desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com esta Trilha e este nível de dificuldade."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/{trilha}/{dificuldade}"})
    ResponseEntity<QuestaoDTO> listByTrailAndDificulty(@PathVariable Integer trilha,
                                                       @PathVariable Integer dificuldade)
                                                        throws BancoDeDadosException;
}
