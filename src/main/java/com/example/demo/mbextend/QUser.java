package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlField;
import com.example.demo.mbextend.sqlparts.SqlTaBle;

import java.util.Arrays;
import java.util.List;

public class QUser implements SqlTaBle {
	private static final String tableName = "user";
	private String tableAlias;

	public QField id;
	public QField name;
	public QField birthday;

    public QUser() {
        this(null);
    }

	public QUser(String columnPrefix) {
		this.id = new QField(null,"id",null,columnPrefix);
		this.name = new QField(null,"name",null,columnPrefix);
		this.birthday = new QField(null,"birthday",null,columnPrefix);
    }

	@Override
	public String getTableName() {
		return QUser.tableName;
	}

	@Override
	public String getTableAlias() {
		return tableAlias;
	}

    @Override
    public List<SqlField> getAllSqlField() {
        return Arrays.asList(id,name,birthday);
    }

	@Override
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
}
