package Ch_039.Controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by igortsapyak on 28.04.16.
 */
@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String page(){
        return "index";
    }





}
