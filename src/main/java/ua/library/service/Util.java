package ua.library.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class Util {
    private static final String[] letters = { "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};

    public static String[] getLetters() {
        return letters;
    }

    public String getPath(HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getAttribute("javax.servlet.forward.request_uri"));
        String param = "";
        if(request.getQueryString() != null) {
            param = URLDecoder.decode(request.getQueryString(), "UTF-8").replaceAll("&?page=+[1-9]", "");
        }
        buffer.append("?");
        if(param != null && !param.isEmpty()){
            String[] temp = param.split("&");
            //id=1&page=2
            for(String s: temp){
                buffer.append(s).append("&");
            }
        }

        return buffer.toString();
    }
}
