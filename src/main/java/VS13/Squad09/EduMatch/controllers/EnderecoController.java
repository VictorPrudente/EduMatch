package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IEnderecoController;
import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Validated
@RestController
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
                                                 EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(enderecoService.atualizar(idEndereco, enderecoCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) throws BancoDeDadosException {
        return ResponseEntity.ok(enderecoService.deletar(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<EnderecoDTO> listarPorDono(@PathVariable("id") Integer id) throws BancoDeDadosException {
        return ResponseEntity.ok(enderecoService.listarPorDono(id));
    }
}