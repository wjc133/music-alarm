package cn.ellacat.tools.alarm.job;

import cn.ellacat.tools.alarm.netease.NeteaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * @author wjc133
 */
@Lazy(false)
@EnableScheduling
@Service
public class AlarmJob {
    @Autowired
    private NeteaseClient client;
}
