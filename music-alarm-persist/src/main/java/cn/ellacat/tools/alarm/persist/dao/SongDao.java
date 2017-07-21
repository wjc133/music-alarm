package cn.ellacat.tools.alarm.persist.dao;

import cn.ellacat.tools.alarm.persist.SongMeta;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wjc133
 */
@Repository
public class SongDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongDao.class);

    private final Dao<SongMeta, Long> dao;

    @Autowired
    public SongDao(JdbcConnectionSource source) throws SQLException {
        this.dao = DaoManager.createDao(source, SongMeta.class);
        TableUtils.createTableIfNotExists(source, SongMeta.class);
    }


    public boolean insert(SongMeta meta) {
        try {
            dao.create(meta);
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public boolean insertOrUpdate(SongMeta meta) {
        try {
            dao.createOrUpdate(meta);
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public boolean insertIfNotExist(SongMeta meta) {
        try {
            Map<String, Object> values = new HashMap<>();
            values.put("playlist_id", meta.getPlaylistId());
            values.put("ori_id", meta.getOriId());
            List<SongMeta> oriMeta = dao.queryForFieldValues(values);
            if (oriMeta == null || oriMeta.size() == 0) {
                dao.create(meta);
            }
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public SongMeta query(long id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            LOGGER.error("insert error: id={}", id, e);
        }
        return null;
    }

    public List<SongMeta> queryByPlaylistId(int playlistId) {
        try {
            return dao.queryForEq("playlist_id", playlistId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
