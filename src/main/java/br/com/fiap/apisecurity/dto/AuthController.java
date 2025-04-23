import br.com.fiap.apisecurity.dto.RegisterDTO;
 import br.com.fiap.apisecurity.model.User;
 import br.com.fiap.apisecurity.repository.UserRepository;
 import br.com.fiap.apisecurity.service.TokenService;
 import jakarta.validation.Valid;
 import org.apache.coyote.Response;
 import org.springframework.beans.factory.annotation.Autowired;
 @@ -23,14 +24,17 @@ public class AuthController {
     private AuthenticationManager authenticationManager;
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private TokenService tokenService;
 
     @PostMapping("/login")
     public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
         var userPwd = new UsernamePasswordAuthenticationToken(
                 authDTO.username(),
                 authDTO.password());
         //var auth = this.authenticationManager.authenticate(userPwd);
         return ResponseEntity.ok().build();
         var auth = this.authenticationManager.authenticate(userPwd);
         var token = tokenService.generateToken((User) auth.getPrincipal());
         return ResponseEntity.ok(token);
     }
 
     @PostMapping("/register")