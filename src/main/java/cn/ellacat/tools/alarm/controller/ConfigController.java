package cn.ellacat.tools.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wjc133
 */
@Controller
@RequestMapping("mgr")
public class ConfigController {
    @RequestMapping("index")
    public String toIndexPage() {
        return "index";
    }
}
