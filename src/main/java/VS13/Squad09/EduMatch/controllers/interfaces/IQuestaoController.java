package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
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


    @Operation(summary = "Lista as questões de maneira paginada.", description = "Lista as questões paginadas através de uma trilha específica, uma trilha e uma dificuldade específica ou todas as questões.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<Page<QuestaoDTO>> listarQuestoes(

            @Parameter(name = "status",
                    description = "Status da questão.",
                    examples = {
                            @ExampleObject(name = "Ativa", value = "1"),
                            @ExampleObject(name = "Inativa", value = "0")},
                    allowEmptyValue = true)
            @RequestParam(required = false) Integer status,

            @Parameter(name = "trilha",
                    description = "Trilha das questões",
                    examples = {
                            @ExampleObject(name = "Português", value = "0"),
                            @ExampleObject(name = "Matemática", value = "1"),
                            @ExampleObject(name = "Soft Skills", value = "2"),
                            @ExampleObject(name = "Geografia", value = "3"),
                            @ExampleObject(name = "História", value = "4"),
                            @ExampleObject(name = "Física", value = "5"),
                            @ExampleObject(name = "Química", value = "6"),
                            @ExampleObject(name = "Biologia", value = "7"),
                            @ExampleObject(name = "Atualidades", value = "8"),
                            @ExampleObject(name = "Espanhol", value = "9"),
                            @ExampleObject(name = "Inglês", value = "10")},
                    allowEmptyValue = true)
            @RequestParam(required = false) Integer trilha,

            @Parameter(name = "dificuldade",
                    description = "Dificuldade da questão",
                    examples = {
                            @ExampleObject(name = "Fácil", value = "0"),
                            @ExampleObject(name = "Médio", value = "1"),
                            @ExampleObject(name = "Difícil", value = "2")},
                    allowEmptyValue = true)
            @RequestParam(required = false) Integer dificuldade,

            @Parameter(name = "sort",
                    description = "Ordenamento das questões.",
                    allowEmptyValue = true)
            @RequestParam(required = false) String sort,

            @Parameter(name = "page",
                    description = "Index das páginas.")
            @RequestParam(required = false, defaultValue = "0") Integer page,

            @Parameter(name = "size",
                    description = "Quantidade de questões por página.")
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception;
}
