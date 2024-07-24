package formation.conceptdev.facto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import formation.conceptdev.facto.services.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/email")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("")
    public String sendEmail(@RequestParam Integer id) {
       
        
        String recipientEmail = "efaure.courrier@gmail.com"; // Remplacez par le destinataire réel
        String sujet = "test mail";
        String body = "This is an email sent for ID: ";
        
        emailService.sendSimpleMessage(recipientEmail, sujet, body);
        return "Email sent successfully!";
    }
}