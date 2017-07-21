package cn.ellacat.tools.alarm.server.controller;

import cn.ellacat.tools.alarm.server.service.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc133
 */
@RestController
@RequestMapping("music")
public class MusicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update() {
        LOGGER.info("updating music library");
        musicService.updateSongLibrary();
        return "update";
    }
}
