package org.activiti.engine.impl.persistence;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ByteArrayRef;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeReference;
import org.postgresql.util.PGobject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * MyBatis TypeHandler for {@link Map<String,Object>}.
 * 
 */
public class MapTypeHandler extends TypeReference<Map<String,Object>> implements TypeHandler<Map<String,Object>> {

  @Override
  public void setParameter(PreparedStatement ps, int i, Map<String,Object> parameter, JdbcType jdbcType) throws SQLException {
    ps.setObject(i, getValueToSet(parameter));
  }

  private Object getValueToSet(Map<String,Object> parameter) throws SQLException {
    if (parameter == null) {
      return null;
    }
    Gson gson=new Gson();
    PGobject json = new PGobject();
	json.setType("jsonb");
	json.setValue(gson.toJson(parameter));
    return json;
  }
  
  @Override
  public Map<String,Object> getResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    Gson gson=new Gson();
    return gson.fromJson(value, new TypeToken<Map<String,Object>>(){}.getType());
  }

  @Override
  public Map<String,Object> getResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    Gson gson=new Gson();
    return gson.fromJson(value, new TypeToken<Map<String,Object>>(){}.getType());
  }

  @Override
  public Map<String,Object> getResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    Gson gson=new Gson();
    return gson.fromJson(value, new TypeToken<Map<String,Object>>(){}.getType());
  }

}
