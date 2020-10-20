package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql,Object... parameters) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            int i = 0;
            for (Object parameter : parameters) {
                i++;
                pstm.setObject(i,parameter);
            }
            if (sql.startsWith("SELECT")){
                return (T) pstm.executeQuery();//reference data type
            }
            return (T) ((Boolean)(pstm.executeUpdate()>0));//Booleon(Primitive)boxing
    }

}
