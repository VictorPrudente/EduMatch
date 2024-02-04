package VS13.Squad09.EduMatch.dtos.mapper;

import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContatoMapper{
    private final ObjectMapper mapper;

    public Contato toEntity(ContatoCreateDTO dto){
        Contato entity = mapper.convertValue(dto, Contato.class);
        return entity;
    }

    public ContatoDTO toDto(Contato entity){
        ContatoDTO dto = mapper.convertValue(entity, ContatoDTO.class);
        return dto;
    }

    public List<ContatoDTO> toDto(List<Contato> contatos){
        return contatos.stream()
                .map(contato -> toDto(contato))
                .collect(Collectors.toList());
    }
}
