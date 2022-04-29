package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlField;
import com.example.demo.mbextend.sqlparts.SqlTaBle;

import java.util.Arrays;
import java.util.List;

public class QEmployee implements SqlTaBle {
	private static final String tableName = "employee";
	private String tableAlias;

	public QField id;
	public QField empNumber;
	public QField leaderNumber;
	public QField empName;

    public QEmployee() {
        this(null);
    }

	public QEmployee(String columnPrefix) {
		this.id = new QField(null,"id",null,columnPrefix);
		this.empNumber = new QField(null,"emp_number",null,columnPrefix);
		this.leaderNumber = new QField(null,"leader_number",null,columnPrefix);
		this.empName = new QField(null,"emp_name",null,columnPrefix);
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
    public List<SqlField> getAllSqlField() {
        return Arrays.asList(id,empNumber,leaderNumber,empName);
    }

	@Override
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
}
