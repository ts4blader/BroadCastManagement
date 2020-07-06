package dao;

import dto.NhaSX;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhaSXDAO implements IDAO<NhaSX> {
	private static NhaSXDAO instance;
	
	private NhaSXDAO () {
	}
	
	public static NhaSXDAO getInstance () {
		if (instance == null)
		{
			instance = new NhaSXDAO();
		}
		return instance;
	}
	private JdbcRowSet rowSet = Connector.createRowSet("select * from NHASX");

	@Override
	public List<NhaSX> getAll() {
		List<NhaSX> rowList = new ArrayList<NhaSX>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				NhaSX row1 = new NhaSX (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}

		return rowList;
	}

	@Override
	public NhaSX getByID(String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new NhaSX (rowSet.getString(1),rowSet.getString(2),rowSet.getString(3) );
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(NhaSX t) {
		try {
			rowSet.moveToInsertRow();
			rowSet.updateString("maNSX", t.getMaNSX());
			rowSet.updateString("tenSNX", t.getTenNSX());
			rowSet.updateString("maQG", t.getMaQuocGia());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(NhaSX t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getStringBySTT(1))) {
					rowSet.updateString(2, t.getStringBySTT(2));
					rowSet.updateString(3, t.getStringBySTT(3));
					rowSet.updateRow();
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(NhaSX t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3))) 	
						{
							rowSet.deleteRow();
						}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<NhaSX> getByObj(NhaSX t) {
		List<NhaSX> resultList = new ArrayList<NhaSX>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3)))
						{
							NhaSX tempObj = new NhaSX (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3));
							resultList.add(tempObj);
						}
			}
			
		} catch (SQLException e) {}
		return resultList;
	}

	@Override
	public int getNumberOfRows() {
		try {
			rowSet.last();
			return rowSet.getRow();
		} catch (SQLException e) {}
		return 0;
	}



}
