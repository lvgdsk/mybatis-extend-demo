package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QChinaRegion extends SqlTaBle {
	private static final String tableName = "`china_region`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn pid;
	public final QColumn regionName;
	public final QColumn type;
	public final QColumn postCode;
	public final QColumn regionCode;
	public final QColumn longitude;
	public final QColumn latitude;

    public QChinaRegion() {
        this(null);
    }

	public QChinaRegion(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.pid = new QColumn(tableAlias,"pid",columnPrefix==null?null:columnPrefix+"pid");
		this.regionName = new QColumn(tableAlias,"region_name",columnPrefix==null?null:columnPrefix+"region_name");
		this.type = new QColumn(tableAlias,"type",columnPrefix==null?null:columnPrefix+"type");
		this.postCode = new QColumn(tableAlias,"post_code",columnPrefix==null?null:columnPrefix+"post_code");
		this.regionCode = new QColumn(tableAlias,"region_code",columnPrefix==null?null:columnPrefix+"region_code");
		this.longitude = new QColumn(tableAlias,"longitude",columnPrefix==null?null:columnPrefix+"longitude");
		this.latitude = new QColumn(tableAlias,"latitude",columnPrefix==null?null:columnPrefix+"latitude");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,pid,regionName,type,postCode,regionCode,longitude,latitude);
    }
}
