package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.library.service.LibraryService;
import ua.library.service.Util;

@Controller
@SessionAttributes(value = "pages")
public class MainController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        model.addAttribute("pages", libraryService.getBooksOnPage(0, page, null));
        return "pages/book";
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
