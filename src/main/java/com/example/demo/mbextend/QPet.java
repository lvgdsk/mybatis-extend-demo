package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlField;
import com.example.demo.mbextend.sqlparts.SqlTaBle;

import java.util.Arrays;
import java.util.List;

public class QPet implements SqlTaBle {
	private static final String tableName = "pet";
	private String tableAlias;

	public QField id;
	public QField name;
	public QField uid;
	public QField species;

    public QPet() {
        this(null);
    }

	public QPet(String columnPrefix) {
		this.id = new QField(null,"id",null,columnPrefix);
		this.name = new QField(null,"name",null,columnPrefix);
		this.uid = new QField(null,"uid",null,columnPrefix);
		this.species = new QField(null,"species",null,columnPrefix);
    }

	@Override
	public String getTableName() {
		return QPet.tableName;
	}

	@Override
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	@Override
	public String getTableAlias() {
		return tableAlias;
	}

    @Override
    public List<SqlField> getAllSqlField() {
        return Arrays.asList(id,name,uid,species);
    }
}
