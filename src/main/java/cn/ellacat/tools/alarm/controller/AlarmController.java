package cn.ellacat.tools.alarm.controller;

import cn.ellacat.tools.alarm.alarm.Alarm;
import cn.ellacat.tools.alarm.service.AlarmService;
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
@RequestMapping("alarm")
public class AlarmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private Alarm alarm;
    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "start", method = RequestMethod.POST)
    public String startAlarming() {
        LOGGER.info("starting alarming...");
        alarm.startPlay();
        return "start";
    }

    @RequestMapping(value = "stop", method = RequestMethod.POST)
    public String stopAlarming() {
        LOGGER.info("stopping alarming...");
        alarm.stopPlay();
        return "stop";
    }

    @RequestMapping(value = "ready", method = RequestMethod.POST)
    public String readyMusic() {
        LOGGER.info("ready for alarming...");
        alarmService.addMusicToAlarm();
        return "ready";
    }
}
