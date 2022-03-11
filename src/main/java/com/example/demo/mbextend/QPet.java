package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;

public class QPet implements SqlTaBle {
	private static String tableName = "pet";
	private String tableAlias;
	private String columnPrefix;

	public QField id;
	public QField name;
	public QField uid;
	public QField species;

    public QPet(String tableAlias) {
        this(tableAlias,null);
    }

	public QPet(String tableAlias,String columnPrefix) {
	    this.tableAlias = tableAlias;
	    this.columnPrefix = columnPrefix;
		this.id = new QField(tableAlias,"id",null,columnPrefix);
		this.name = new QField(tableAlias,"name",null,columnPrefix);
		this.uid = new QField(tableAlias,"uid",null,columnPrefix);
		this.species = new QField(tableAlias,"species",null,columnPrefix);
    }

	@Override
	public String getTableName() {
		return QPet.tableName;
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
