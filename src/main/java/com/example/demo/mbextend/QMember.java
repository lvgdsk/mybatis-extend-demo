package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;
import java.util.Arrays;
import java.util.List;

public class QMember implements SqlTaBle {
	private static final String tableName = "member";
	private String tableAlias;

    public QField id;
	public QField username;
	public QField phone;
	public QField birthday;
	public QField gender;

    public QMember() {
        this(null);
    }

	public QMember(String columnPrefix) {
        this.id = QField.column("id",columnPrefix==null?null:columnPrefix+"id");
		this.username = QField.column("username",columnPrefix==null?null:columnPrefix+"username");
		this.phone = QField.column("phone",columnPrefix==null?null:columnPrefix+"phone");
		this.birthday = QField.column("birthday",columnPrefix==null?null:columnPrefix+"birthday");
		this.gender = QField.column("gender",columnPrefix==null?null:columnPrefix+"gender");
    }

	@Override
	public String getTableName() {
		return QMember.tableName;
	}

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

	@Override
	public String getTableAlias() {
		return this.tableAlias;
	}

    @Override
    public List<QField> getQueryColumns() {
        return Arrays.asList(id,username,phone,birthday,gender);
    }
}
