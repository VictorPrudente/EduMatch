package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IEnderecoController;
import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.services.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Validated
@RestController
@Tag(name = "Endereco", description = "Endpoint do CRUD de Endereco")
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController implements IEnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<EnderecoDTO> salvar(@PathVariable("idUsuario") Integer idUsuario,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception{
        return ResponseEntity.ok(enderecoService.salvar(idUsuario, enderecoCreateDTO));
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable("idEndereco") Integer idEndereco,
                                                 @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        return ResponseEntity.ok(enderecoService.atualizar(idEndereco, enderecoCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) throws Exception {
        enderecoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<EnderecoDTO> listarPorDono(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(enderecoService.findEnderecoByIdUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(enderecoService.findById(id));
    }
}