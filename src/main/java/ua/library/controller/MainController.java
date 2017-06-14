package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.dao.UserAccountDAO;
import ua.library.model.UserDto;
import ua.library.model.entities.Account;
import ua.library.service.LibraryService;
import ua.library.service.UserService;
import ua.library.service.Util;

import javax.validation.Valid;

@Controller
@SessionAttributes(value = "pages")
public class MainController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        model.addAttribute("pages", libraryService.getBooksOnPage(0, page, null));
        return "pages/book";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("user", new UserDto());
        return "pages/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationConfirm(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult){
        Account account = null;
        if(!bindingResult.hasErrors()){
            account = userService.registrationUser(userDto);
        }
        if(account == null){
            bindingResult.reject("Пользователь с таким именем/email уже существует");
        }

        return "redirect:pages/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null ) model.addAttribute("error", "Не верный логин или пароль");

        return "pages/login";
    }

    @ModelAttribute
    public void setAttribute(Model model){
        if(!model.containsAttribute("genres") && !model.containsAttribute("letters")){
            model.addAttribute("genres", libraryService.getAllGenre());
            model.addAttribute("letters", Util.getLetters());
        }
    }

}
