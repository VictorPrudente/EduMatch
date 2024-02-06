package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IQuestaoController {


    @Operation(summary = "Criar uma Questão", description = "Cadastra uma questão no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Criou uma questão."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não criada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não criada.")})
    @PostMapping
    ResponseEntity<QuestaoDTO> create(@RequestBody @Valid QuestaoCreateDTO questaoCreateDTO)
            throws BancoDeDadosException;


    @Operation(summary = "Atualizar uma Questão", description = "Atualiza uma questão no banco de dados passando seu id como parâmetro, tornando a versão antiga inativa e criando uma nova versão da mesma.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Questão atualizada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não atualizada."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com este ID. Questão não atualizada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não atualizada.")})
    @PutMapping("{id}")
    ResponseEntity<QuestaoDTO> update(@PathVariable Integer id,
                                      @RequestBody @Valid QuestaoCreateDTO questaoCreateDTO)
            throws BancoDeDadosException, NaoEncontradoException;

    @Operation(summary = "Torna uma questão inativa.", description = "Atualiza o status de uma questão no banco de dados para inativa, passando seu id como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Questão atualizada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Questão não atualizada."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com este ID. Questão não atualizada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Questão não atualizada.")})
    @DeleteMapping("/{id}")
    ResponseEntity<QuestaoDTO> delete(@PathVariable Integer id)
            throws BancoDeDadosException, NaoEncontradoException;


    @Operation(summary = "Listar uma questão pelo seu ID.", description = "Lista uma questão passando como parâmetro ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com este ID."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/{id}"})
    ResponseEntity<QuestaoDTO> findById(@PathVariable Integer id)
            throws BancoDeDadosException, NaoEncontradoException;


    @Operation(summary = "Listar uma questão pela sua dificuldade e sua trilha.", description = "Lista uma questão passando como parâmetro sua trilha e o nível da dificuldade desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com esta Trilha e este nível de dificuldade."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/{trilha}/{dificuldade}"})
    ResponseEntity<QuestaoDTO> findByTrailAndDificulty(@PathVariable Integer trilha,
                                                       @PathVariable Integer dificuldade)
            throws BancoDeDadosException, NaoEncontradoException;


    @Operation(summary = "Listar todas as questão com a mesma dificuldade da mesma trilha.", description = "Lista todas as questão passando como parâmetro sua trilha e o nível da dificuldade desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com esta Trilha e este nível de dificuldade."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/all/{trilha}/{dificuldade}"})
    ResponseEntity<List<QuestaoDTO>> findAllByTrailAndDificulty(@PathVariable Integer trilha,
                                                                @PathVariable Integer dificuldade)
            throws BancoDeDadosException;


    @Operation(summary = "Listar todas as questões de uma determinada trilha.", description = "Lista todas as questão passando como parâmetro sua trilha ordenada pela dificuldade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado uma questão com esta Trilha."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/{trilha}"})
    ResponseEntity<List<QuestaoDTO>> findAllByTrail(@PathVariable Integer trilha)
            throws BancoDeDadosException;

    @Operation(summary = "Listar todas as questões do banco de dados pelo seu status.", description = "Lista todas as questão do banco de dados ativas ou inativas passando como parâmetro 0 ou 1.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping({"/all/{status}"})
    ResponseEntity<List<QuestaoDTO>> findAllByStatus(@PathVariable Integer status)
            throws BancoDeDadosException;
}
