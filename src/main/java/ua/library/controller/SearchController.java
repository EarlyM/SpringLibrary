package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.library.model.Pages;
import ua.library.service.LibraryService;
import ua.library.service.Util;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/searchCriterion")
public class SearchController {
    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String searchBook(@ModelAttribute("pages") Pages pages,@RequestParam String search, @RequestParam String select,@RequestParam(value = "page", defaultValue = "1") Integer page){
        pages.setSelectPage(page);
        libraryService.getBookByText(search, select, pages);
        return "pages/book";
    }

    @RequestMapping(value = "/genre", method = RequestMethod.GET)
    public String searchBookByGenre(@ModelAttribute("pages") Pages pages,@RequestParam("id") Long id,@RequestParam(value = "page", defaultValue = "1") Integer page){
        pages.setSelectPage(page);
        libraryService.getBookByGenre(id, pages);
        return "pages/book";
    }

    @RequestMapping(value = "/letter", method = RequestMethod.GET)
    public String searchBookByLetter(@ModelAttribute("pages") Pages pages,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam("letter") Character letter){
        pages.setSelectPage(page);
        libraryService.getBookByLetter(letter, pages);
        return "pages/book";
    }

    @ModelAttribute
    public void setSessionAttribute(HttpServletRequest request){
        if(request.getSession().isNew()){
            request.getSession().setAttribute("genres", libraryService.getAllGenre());
            request.getSession().setAttribute("letters", Util.getLetters());
        }
    }

}
