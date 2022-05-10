package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QMember extends SqlTaBle {
	private static final String tableName = "`member`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn username;
	public final QColumn phone;
	public final QColumn birthday;
	public final QColumn gender;
	public final QColumn money;
	public final QColumn addressCode;
	public final QColumn addressName;

    public QMember() {
        this(null);
    }

	public QMember(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.username = new QColumn(tableAlias,"username",columnPrefix==null?null:columnPrefix+"username");
		this.phone = new QColumn(tableAlias,"phone",columnPrefix==null?null:columnPrefix+"phone");
		this.birthday = new QColumn(tableAlias,"birthday",columnPrefix==null?null:columnPrefix+"birthday");
		this.gender = new QColumn(tableAlias,"gender",columnPrefix==null?null:columnPrefix+"gender");
		this.money = new QColumn(tableAlias,"money",columnPrefix==null?null:columnPrefix+"money");
		this.addressCode = new QColumn(tableAlias,"address_code",columnPrefix==null?null:columnPrefix+"address_code");
		this.addressName = new QColumn(tableAlias,"address_name",columnPrefix==null?null:columnPrefix+"address_name");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,username,phone,birthday,gender,money,addressCode,addressName);
    }
}
