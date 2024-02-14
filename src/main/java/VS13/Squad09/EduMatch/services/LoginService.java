//package VS13.Squad09.EduMatch.services;
//import VS13.Squad09.EduMatch.dtos.LoginDTO;
//import VS13.Squad09.EduMatch.entities.Login;
//import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
//import VS13.Squad09.EduMatch.repositories.LoginRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class LoginService {
//    private final LoginRepository loginRepository;
//    private final ObjectMapper objectMapper;
//
//    public LoginDTO create (LoginDTO loginDTO){
//        if (getIdLoggedUser().equals(null)) {
//            Login login = objectMapper.convertValue(loginDTO, Login.class);
//            return objectMapper.convertValue(loginRepository.save(login), LoginDTO.class);
//        } return null;
//    }
//
//    public Optional<Login> findByLoginAndSenha(String login, String senha) {
//        return loginRepository.findByLoginAndSenha(login, senha);
//    }
//
//    public Login findById(Integer idLogin) throws RegraDeNegocioException {
//        return loginRepository.findById(idLogin).orElseThrow(() -> new RegraDeNegocioException("Login não encontrada"));
//    }
//
//    public Optional<Login> findByLogin(String login){
//        return loginRepository.findByEmail(login);
//    }
//
//
//    public Integer getIdLoggedUser(){
//        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//    }
//
//    public Login getLoggedUser() throws RegraDeNegocioException {
//        return findById(getIdLoggedUser());
//    }
//}
