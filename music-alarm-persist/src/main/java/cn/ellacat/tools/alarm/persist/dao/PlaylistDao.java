package cn.ellacat.tools.alarm.persist.dao;

import cn.ellacat.tools.alarm.persist.PlaylistMeta;
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
import java.util.List;

/**
 * @author wjc133
 */
@Repository
public class PlaylistDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongDao.class);

    private final Dao<PlaylistMeta, Long> dao;

    @Autowired
    public PlaylistDao(JdbcConnectionSource source) throws SQLException {
        this.dao = DaoManager.createDao(source, PlaylistMeta.class);
        TableUtils.createTableIfNotExists(source, PlaylistMeta.class);
    }

    public boolean insert(PlaylistMeta meta) {
        try {
            dao.create(meta);
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public boolean insertOrUpdate(PlaylistMeta meta) {
        try {
            dao.createOrUpdate(meta);
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public boolean insertIfNotExist(PlaylistMeta meta) {
        try {
            List<PlaylistMeta> oriMeta = dao.queryForEq("ori_id", meta.getOriId());
            if (oriMeta == null || oriMeta.size() == 0) {
                dao.create(meta);
            }
            return true;
        } catch (SQLException e) {
            LOGGER.error("insert error: meta={}", meta, e);
        }
        return false;
    }

    public PlaylistMeta query(long id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            LOGGER.error("insert error: id={}", id, e);
        }
        return null;
    }

    public PlaylistMeta queryByOriId(int oriId) {
        try {
            List<PlaylistMeta> metas = dao.queryForEq("ori_id", oriId);
            if (metas != null && metas.size() > 0) {
                return metas.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error("queryByOriId error. oriId={}", oriId, e);
        }
        return null;
    }

    public List<PlaylistMeta> queryAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
