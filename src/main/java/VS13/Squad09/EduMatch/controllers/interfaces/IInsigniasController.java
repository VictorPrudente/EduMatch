package VS13.Squad09.EduMatch.controllers.interfaces;


import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@Tag(name = "Insignias", description = "Rotas privadas para Administradores autenticados.")
public interface IInsigniasController {

    @Operation(summary = "Criar uma Insígnia", description = "Cria uma nova insígnia no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida. Insígnia criada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Insígnia não cadastrada."),
            @ApiResponse(responseCode = "409", description = "Restrição de valor único violada. Insígnia não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Insígnia não cadastrada.")})
    @PostMapping
    ResponseEntity<InsigniaDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception;


    @Operation(summary = "Listar todas ou uma insígnia de um Usuário", description = "Recebe no parâmetro de pesquisa o ID de um usuário, podendo receber ou não o ID de uma insígnia." +
            "Caso não seja passado o ID de uma insígnia, irá listar todas as insígnias adquiridas por aquele usuário, trazendo em cada uma a URL de sua imagem e seu título. Caso seja passado o ID válido de uma insígnia que o usuário possua, irá trazer os dados completos daquela insígnia. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com este ID."),
            @ApiResponse(responseCode = "404", description = "Nenhuma insígnia com este ID foi adquirida pelo usuário."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping("/usuario")
    ResponseEntity<List<InsigniaDTO>> listarPorUsuario(@RequestParam Integer usuario,
                                                         @RequestParam(required = false) Integer insignia);
}
