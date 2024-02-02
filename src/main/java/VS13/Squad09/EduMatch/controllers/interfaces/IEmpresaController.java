package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.response.PessoaJuridicaDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IEmpresaController {

    @Operation(summary = "Listar empresas", description = "Lista as empresas cadastradas no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listou as empresas cadastradas"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Endereço não criado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Endereço não criado.")})
    @GetMapping()
    ResponseEntity<List<PessoaJuridicaDTO>> listarEmpresas () throws Exception;
}
