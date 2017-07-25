package cn.ellacat.tools.alarm.server.controller;

import cn.ellacat.tools.alarm.server.service.VoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc133
 */
@RestController
@RequestMapping("card")
public class AudioCardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioCardController.class);

    @Autowired
    private VoiceService voiceService;

    @GetMapping("start")
    public String startCapture() {
        voiceService.start();
        return "started";
    }

    @GetMapping("stop")
    public String stopCapture() {
        voiceService.stopAndDistinguish();
        return "stopped";
    }
}
