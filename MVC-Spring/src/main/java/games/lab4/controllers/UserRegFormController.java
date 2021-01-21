package games.lab4.controllers;

import games.lab4.models.User;
import games.lab4.repository.UserRep;
import games.lab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserRegFormController {

    @Autowired
   private UserService userService;
@Autowired
    private UserRep userRep;





    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userCommand", new User());
        model.addAttribute("activePage","regPanel");
        return "user/regForm";

    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) throws MessagingException {
        if(bindingResult.hasErrors())
        {
            return "user/regForm";
        }

        userService.saveUser(userForm);
        return "user/regSucces";
    }

    @GetMapping("/registrationConf")
            public String confirmRegist(Model model, @RequestParam("token") String token,@RequestParam("userName") String name)
    {
        var user=userRep.findByUsername(name);

        if(user.getActivationCode().equals(token))
        {
            user.setPasswordConfirm(user.getPassword());
            user.setEnabled(true);
            userRep.save(user);
            return "user/regConfSucces";
        }
        return "redirect:/login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.setDisallowedFields("enabled","roles");
    }
}
