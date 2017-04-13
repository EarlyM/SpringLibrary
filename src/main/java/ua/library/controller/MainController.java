package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.library.model.Pages;
import ua.library.model.entities.Book;
import ua.library.service.LibraryService;
import ua.library.service.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes(value = "pages")
public class MainController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("pages") Pages pages,@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        pages.setSelectPage(page);
        libraryService.getAllBook(pages);
        model.addAttribute("pages", pages);
        return "pages/book";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null ) model.addAttribute("error", "Не верный логин или пароль");

        return "pages/login";
    }

    @ModelAttribute
    public Pages getPager(){
        return new Pages();
    }

    @ModelAttribute
    public void setAttribute(Model model){
        if(!model.containsAttribute("genres") && !model.containsAttribute("letters")){
            model.addAttribute("genres", libraryService.getAllGenre());
            model.addAttribute("letters", Util.getLetters());
        }
    }

}
