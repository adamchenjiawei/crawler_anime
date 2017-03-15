package anime_chuixue.handler;

import java.sql.ResultSet;
import java.util.List;

//统一处理结果集
@SuppressWarnings("rawtypes")
public interface ResultSetHandler {
	List handler(ResultSet rs) throws Exception;
}
