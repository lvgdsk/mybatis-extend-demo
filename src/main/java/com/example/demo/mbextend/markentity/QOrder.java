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
	public final QColumn createTime;
	public final QColumn memberId;
	public final QColumn totalPrice;
	public final QColumn status;

    public QOrder() {
        this(null);
    }

	public QOrder(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.createTime = new QColumn(tableAlias,"create_time",columnPrefix==null?null:columnPrefix+"create_time");
		this.memberId = new QColumn(tableAlias,"member_id",columnPrefix==null?null:columnPrefix+"member_id");
		this.totalPrice = new QColumn(tableAlias,"total_price",columnPrefix==null?null:columnPrefix+"total_price");
		this.status = new QColumn(tableAlias,"status",columnPrefix==null?null:columnPrefix+"status");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,createTime,memberId,totalPrice,status);
    }
}
