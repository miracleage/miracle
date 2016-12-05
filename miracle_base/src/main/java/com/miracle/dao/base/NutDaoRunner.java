package com.miracle.dao.base;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.DaoRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import org.springframework.jdbc.datasource.DataSourceUtils;
/**
 * Created by yangchao2014 on 2016/11/28.
 */
public class NutDaoRunner  implements DaoRunner{

    public void run(DataSource dataSource, ConnCallback callback) {
        Connection con = DataSourceUtils.getConnection(dataSource);
        try {
            callback.invoke(con);
        } catch (Exception e) {
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            else
                throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }
}
