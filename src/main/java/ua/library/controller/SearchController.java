package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.library.model.Pagination;
import ua.library.service.LibraryService;
import ua.library.service.Util;

@Controller
@RequestMapping(value = "/searchCriterion")
public class SearchController {
    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String searchBook(@RequestParam String search, @RequestParam String select, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model){
        Pagination pagination = null;
        switch (select){
            case "book":
                pagination = libraryService.getBooksOnPage(LibraryService.TEXT_CRITERIA,page,search);
                break;
            case "author":
                pagination = libraryService.getBooksOnPage(LibraryService.AUTHOR_CRITERIA, page, search);
                break;
        }

        model.addAttribute("pages", pagination);
        return "pages/book";
    }

    @RequestMapping(value = "/genre", method = RequestMethod.GET)
    public String searchBookByGenre(@RequestParam("id") Long id, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model){
        model.addAttribute("pages", libraryService.getBooksOnPage(LibraryService.GENRE_CRITERIA, page, id));
        return "pages/book";
    }

    @RequestMapping(value = "/letter", method = RequestMethod.GET)
    public String searchBookByLetter(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam("letter") Character letter, Model model){
        model.addAttribute("pages", libraryService.getBooksOnPage(LibraryService.LETTER_CRITERIA, page, letter));
        return "pages/book";
    }

    @ModelAttribute
    public void setAttribute(Model model){
        if(!model.containsAttribute("genres") && !model.containsAttribute("letters")){
            model.addAttribute("genres", libraryService.getAllGenre());
            model.addAttribute("letters", Util.getLetters());
        }
    }

}
