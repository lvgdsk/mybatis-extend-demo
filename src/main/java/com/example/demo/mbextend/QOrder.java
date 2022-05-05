package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;
import java.util.Arrays;
import java.util.List;

public class QOrder implements SqlTaBle {
	private static final String tableName = "order";
	private String tableAlias;

    public QField id;
	public QField createTime;
	public QField memberId;
	public QField totalPrice;
	public QField status;

    public QOrder() {
        this(null);
    }

	public QOrder(String columnPrefix) {
        this.id = QField.column("id",columnPrefix==null?null:columnPrefix+"id");
		this.createTime = QField.column("create_time",columnPrefix==null?null:columnPrefix+"create_time");
		this.memberId = QField.column("member_id",columnPrefix==null?null:columnPrefix+"member_id");
		this.totalPrice = QField.column("total_price",columnPrefix==null?null:columnPrefix+"total_price");
		this.status = QField.column("status",columnPrefix==null?null:columnPrefix+"status");
    }

	@Override
	public String getTableName() {
		return QOrder.tableName;
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
        return Arrays.asList(id,createTime,memberId,totalPrice,status);
    }
}
