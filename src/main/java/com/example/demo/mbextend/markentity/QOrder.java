package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QOrder extends SqlTaBle {
	private static final String tableName = "`order`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn createTimes;
	public final QColumn memberId;
	public final QColumn totalPrice;
	public final QColumn status;
	public final QColumn addressName;
	public final QColumn addressCode;

    public QOrder() {
        this(null);
    }

	public QOrder(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.createTimes = new QColumn(tableAlias,"create_times",columnPrefix==null?null:columnPrefix+"create_times");
		this.memberId = new QColumn(tableAlias,"member_id",columnPrefix==null?null:columnPrefix+"member_id");
		this.totalPrice = new QColumn(tableAlias,"total_price",columnPrefix==null?null:columnPrefix+"total_price");
		this.status = new QColumn(tableAlias,"status",columnPrefix==null?null:columnPrefix+"status");
		this.addressName = new QColumn(tableAlias,"address_name",columnPrefix==null?null:columnPrefix+"address_name");
		this.addressCode = new QColumn(tableAlias,"address_code",columnPrefix==null?null:columnPrefix+"address_code");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,createTimes,memberId,totalPrice,status,addressName,addressCode);
    }
}
