package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.prova.ProvaFinishCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaFinishDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Provas", description = "Rotas privadas para Usuários e Administradores autenticados")
public interface IProvaController {


    @Operation(summary = "Criar e iniciar uma prova.", description = "Cadastra uma prova no banco de dados recebendo um body Com: idUsuario, trilha(0-10) e dificuldade (0-2), retornando uma prova com X questões, o horário de inicio e um tempo limite.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Criou e inicializou uma prova."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Prova não criada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Prova não criada.")})
    @PostMapping("/start")
    ResponseEntity<ProvaStartDTO> startTest(@RequestBody ProvaStartCreateDTO provaStartCreateDTO) throws Exception;

    @Operation(summary = "Finalizar a prova.", description = "Finaliza uma prova, recebendo via parâmetro o ID da prova e via Body: O Id do usuário que a respondeu e a lista de respostas. O retorno oferece a pontuação que o usuário recebeu, a quantia de perguntas acertadas, o total de questões da prova e os dados do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Prova finalizada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Prova não finalizada."),
            @ApiResponse(responseCode = "404", description = "Nenhuma prova com este ID foi encontrada. Prova não finalizada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Prova não finalizada.")})
    @PutMapping("/{idProva}")
    ResponseEntity<ProvaFinishDTO> finishTest(@PathVariable Integer idProva, @RequestBody ProvaFinishCreateDTO prova) throws Exception;
}
