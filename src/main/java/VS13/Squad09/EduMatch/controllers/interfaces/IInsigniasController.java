package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.insignia.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.insignia.response.InsigniaDetailedDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IInsigniasController {

    @Operation(summary = "Criar uma Insignia / Rota ADM", description = "Cria uma nova insignia no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Insignia criada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Insignia não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Insignia não cadastrada.")})
    @PostMapping
    ResponseEntity<InsigniaDetailedDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception;

    @Operation(summary = "Listar todas ou uma insígnia", description = "Podendo receber ou não id no parâmetro de pesquisa," +
            " este método retornará todas insígnias do banco trazendo consigo a URL da sua imagem e seu título ou, caso seja passado um ID válido, uma insígnia com suas informações completas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrada uma insígnia com este ID."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<List<Object>> listarInsignias(@RequestParam(required = false) Integer id) throws Exception;

    @Operation(summary = "Listar todas ou uma insígnia de um Usuário", description = "Recebe no parâmetro de pesquisa o ID de um usuário, podendo receber ou não o ID de uma insígnia." +
            "Caso não seja passado o ID de uma insígnia, irá listar todas as insígnias adquiridas por aquele usuário, trazendo em cada uma a URL de sua imagem e seu título. Caso seja passado o ID válido de uma insígnia que o usuário possua, irá trazer os dados completos daquela insígnia. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com este ID."),
            @ApiResponse(responseCode = "404", description = "Nenhuma insígnia com este ID foi adquirida pelo usuário."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping("/usuario")
    ResponseEntity<List<Object>> listarPorUsuario(@RequestParam Integer usuario,
                                                         @RequestParam(required = false) Integer insignia) throws Exception;

}
