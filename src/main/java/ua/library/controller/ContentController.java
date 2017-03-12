package ua.library.controller;

import org.apache.taglibs.standard.tag.common.core.OutSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.library.service.LibraryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/img={id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] showImage(@PathVariable("id") Long id){
        return libraryService.getImage(id);
    }

    @RequestMapping(value = "/read={id}", method = RequestMethod.GET)
    public void readBook(@PathVariable("id") Long id, HttpServletResponse response){
        response.setContentType("application/pdf");
        try(OutputStream out = response.getOutputStream()) {
            byte[] content = libraryService.getBookContent(id);
            response.setContentLength(content.length);
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/download/id={id}&name={name}", method = RequestMethod.GET)
    public void downloadBook(@PathVariable("id") Long id, @PathVariable("name") String name, HttpServletResponse response){
        try(OutputStream out = response.getOutputStream()) {
            byte[] content = libraryService.getBookContent(id);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".pdf");
            response.setContentLength(content.length);
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
