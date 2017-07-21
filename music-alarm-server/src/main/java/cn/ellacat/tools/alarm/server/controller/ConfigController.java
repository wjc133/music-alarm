package cn.ellacat.tools.alarm.server.controller;

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
