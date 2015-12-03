package com.ua.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 * Time: 21:54
 */
public interface Mapper<T> {
    T map(ResultSet set) throws SQLException;
}
