package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;

public class QEmployee implements SqlTaBle {
	private static String tableName = "employee";
	private String tableAlias;
	private String columnPrefix;

	public QField id;
	public QField empNumber;
	public QField leaderNumber;
	public QField empName;

    public QEmployee(String tableAlias) {
        this(tableAlias,null);
    }

	public QEmployee(String tableAlias,String columnPrefix) {
	    this.tableAlias = tableAlias;
	    this.columnPrefix = columnPrefix;
		this.id = new QField(tableAlias,"id",null,columnPrefix);
		this.empNumber = new QField(tableAlias,"emp_number",null,columnPrefix);
		this.leaderNumber = new QField(tableAlias,"leader_number",null,columnPrefix);
		this.empName = new QField(tableAlias,"emp_name",null,columnPrefix);
    }

	@Override
	public String getTableName() {
		return QEmployee.tableName;
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
