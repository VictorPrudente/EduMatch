//package VS13.Squad09.EduMatch.controllers.interfaces;
//
//import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
//import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//public interface IContatoController {
//    @Operation(summary = "Listar o contato pelo ID do seu usuário", description = "Retorna uma lista de contatos do banco correspondentes ao ID daquele usuário.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
//            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
//            @ApiResponse(responseCode = "404", description = "Nenhum contato encontrado para o usuário deste ID."),
//            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")}
//    )
//    @GetMapping("/contato/{idUsuario}")
//    ResponseEntity<ContatoDTO> listarPorUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception;
//
//    @Operation(summary = "Criar um Contato", description = "Cadastra um contato no banco de dados.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Criou um contato"),
//            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Contato não criado."),
//            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Contato não criado.")}
//    )
//    @PostMapping("/contato/{idUsuario}")
//    ResponseEntity<ContatoDTO> salvar(@PathVariable("idUsuario") Integer idUsuario,
//                                      @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception;
//
//
//    @Operation(summary = "Atualizar um Contato", description = "Atualiza um contato no banco de dados passando seu ID como parâmetro.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Contato atualizado."),
//            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Contato não atualizado."),
//            @ApiResponse(responseCode = "404", description = "Não foi encontrado um contato com este ID. Contato não atualizado."),
//            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Contato não atualizado.")}
//    )
//    @PutMapping("/{idContato}")
//    ResponseEntity<ContatoDTO> atualizar(@PathVariable Integer idContato,
//                                         @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception;
//
//
//    @Operation(summary = "Deletar um Contato", description = "Remove um contato no banco de dados passando seu ID como parâmetro.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Contato deletado."),
//            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Contato não deletado."),
//            @ApiResponse(responseCode = "404", description = "Não foi encontrado um endereço com este ID. Contato não deletado."),
//            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Contato não deletado.")}
//    )
//    @DeleteMapping("/{idContato}")
//    ResponseEntity<Void> deletar(@PathVariable Integer idContato) throws Exception;
//}
