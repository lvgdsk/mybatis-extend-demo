package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;

public class QUser implements SqlTaBle {
	private static String tableName = "user";
	private String tableAlias;
	private String columnPrefix;

	public QField id;
	public QField name;
	public QField birthday;

    public QUser(String tableAlias) {
        this(tableAlias,null);
    }

	public QUser(String tableAlias,String columnPrefix) {
	    this.tableAlias = tableAlias;
	    this.columnPrefix = columnPrefix;
		this.id = new QField(tableAlias,"id",null,columnPrefix);
		this.name = new QField(tableAlias,"name",null,columnPrefix);
		this.birthday = new QField(tableAlias,"birthday",null,columnPrefix);
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
    public String getColumnPrefix() {
        return columnPrefix;
    }
}
