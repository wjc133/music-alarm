package cn.ellacat.tools.alarm.job;

import cn.ellacat.tools.alarm.persist.SongMeta;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * @author wjc133
 */
@Lazy(false)
@Service
public class JobTest {
    @Autowired
    private JdbcConnectionSource connectionSource;

    @PostConstruct
    public void init() throws SQLException {
        Dao<SongMeta, String> songDao = DaoManager.createDao(connectionSource, SongMeta.class);
        TableUtils.createTable(songDao);
        SongMeta meta = new SongMeta();
        meta.setId(123);
        meta.setOriId(321);
        meta.setAlbum("销售");
        meta.setArtists("zjl");
        meta.setDuration(123123);
        meta.setSize(11111);
        meta.setUrl("http://yy.com");
        meta.setName("火鸡");
        songDao.create(meta);
    }
}
