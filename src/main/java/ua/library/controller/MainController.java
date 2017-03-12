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
    public String index(@ModelAttribute("pages") Pages pages,@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        if(request.getSession().isNew()){
            request.getSession().setAttribute("genres", libraryService.getAllGenre());
            request.getSession().setAttribute("letters", Util.getLetters());
        }
        pages.setSelectPage(page);
        libraryService.getAllBook(pages);
        model.addAttribute("pages", pages);
        return "pages/book";
    }

    @ModelAttribute
    public Pages getPager(){
        return new Pages();
    }

}
