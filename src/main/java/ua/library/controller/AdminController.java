package ua.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.library.model.BookForm;
import ua.library.model.entities.Book;
import ua.library.service.AdministrationService;
import ua.library.service.LibraryService;
import ua.library.service.Util;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private AdministrationService administrationService;


    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String getBookForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model){
        BookForm bookForm;
        if(id != 0){
            Book book = libraryService.findBookById(id);
            bookForm = new BookForm(book);
        } else {
            bookForm = new BookForm();
        }
        model.addAttribute("bookForm", bookForm);
        return "pages/form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("bookForm") BookForm bookForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pages/form";
        }
        administrationService.addBook(bookForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("id") Long id){
        administrationService.deleteBook(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBook(@Valid @ModelAttribute("bookForm") BookForm bookForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "pages/form";
        }
        administrationService.editBook(bookForm);
        return "redirect:/";
    }

    @ModelAttribute
    public void setAttribute(Model model){
        if(!model.containsAttribute("genres") && !model.containsAttribute("letters")){
            model.addAttribute("genres", libraryService.getAllGenre());
            model.addAttribute("letters", Util.getLetters());
        }
    }

}
