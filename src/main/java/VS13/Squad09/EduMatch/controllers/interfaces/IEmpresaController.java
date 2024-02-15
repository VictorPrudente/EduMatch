package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Set;

@Tag(name = "Empresas Parceiras", description = "Rota pública")
public interface IEmpresaController {

    @Operation(summary = "Listar empresas", description = "Lista todas as empresas parceiras cadastradas no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Endereço não criado.")})
    @GetMapping()
    ResponseEntity<Set<UsuarioDTO>> listarEmpresas () throws Exception;
}
