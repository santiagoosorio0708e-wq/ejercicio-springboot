package com.example.sessiondemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
/**
 * Controller for authentication endpoints.
 */
public class AuthController {

    private final UserSession userSession;

    public AuthController(UserSession userSession) {
        this.userSession = userSession;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Aquí se puede agregar validación real. Por ahora es fijo.
        if ("admin".equals(username) && "1234".equals(password)) {
            // Log the user in
            userSession.login(username);
            return "Usuario autenticado exitosamente.";
        }
        return "Credenciales inválidas.";
    }

    @GetMapping("/me")
    public String currentUser() {
        if (!userSession.isLoggedIn()) {
            return "No estás autenticado.";
        }
        SessionUser user = userSession.getUser();
        long elapsedSeconds = (System.currentTimeMillis() - user.getLoginTime()) / 1000;
        return String.format("Usuario: %s\nSesión activa desde hace: %d segundos",
                user.getUsername(), elapsedSeconds);
    }

    @PostMapping("/logout")
    public String logout() {
        if (!userSession.isLoggedIn()) {
            return "No hay sesión activa.";
        }
        userSession.logout();
        return "Sesión cerrada.";
    }
}
