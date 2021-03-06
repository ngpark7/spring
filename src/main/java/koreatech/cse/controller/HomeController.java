package koreatech.cse.controller;

import koreatech.cse.repository.BoardMapper;
import koreatech.cse.repository.NumberMapper;
import koreatech.cse.repository.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @Inject
    private BoardMapper boardMapper;

    @Inject
    private NumberMapper numberMapper;

    @Inject
    private UserMapper userMapper;

    @Value("${env.text}")
    String envText;


    @ModelAttribute("name")
    private String getName() {
        return "IamHomeControllerModelAttribute";
    }

    @RequestMapping("")
    public String home(Model model) {
        System.out.println(envText);
        model.addAttribute("a",envText);
        return "hello";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/number")
    public String number(Model model) {

        model.addAttribute("num",numberMapper.findAll());
        return "number";
    }
    @RequestMapping("/insert")
    public String insert(@RequestParam(name="name") String name, @RequestParam(name="num") int num) {
        numberMapper.insert(name,num);
        return "redirect:/";
    }

    @RequestMapping("/index")
    public String index(Model model){
        // 1. boardMapper에서 board 결과를 가져오고
        model.addAttribute("board",boardMapper.findAll());
        // 2. model 에 board 결과를 싣고 보내면 된다.
        return "index";
    }

    @RequestMapping("/index2")
    public String index2(Model model){

        return "index2";
    }
    @RequestMapping("/jstlTest")
    public String emptyTest(Model model) {
        String a = null;
        String b = "";
        String c = "hello";
        List<String> d = new ArrayList<String>();
        List<String> e = new ArrayList<String>();
        e.add(a);
        e.add(b);

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);
        model.addAttribute("d", d);
        model.addAttribute("e", e);

        List<String> stringArray = new ArrayList<String>();
        stringArray.add("one");
        stringArray.add("two");
        stringArray.add("three");
        model.addAttribute("stringArray", stringArray);

        Map<Integer, String> stringMap = new HashMap<Integer, String>();
        stringMap.put(1, "one");
        stringMap.put(2, "two");
        stringMap.put(3, "three");
        model.addAttribute("stringMap", stringMap);

        return "jstlTest";
    }


}
