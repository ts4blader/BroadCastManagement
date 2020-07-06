package dao;

import dto.LinhVuc;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LinhVucDAO implements IDAO<LinhVuc>{
	private static LinhVucDAO instance;
	
	private LinhVucDAO () {
	}
	
	public static LinhVucDAO getInstance () {
		if (instance == null)
		{
			instance = new LinhVucDAO();
		}
		return instance;
	}
	
	private JdbcRowSet rowSet = Connector.createRowSet("select * from LINHVUC");

	@Override
	public List<LinhVuc> getAll() {
		List<LinhVuc> rowList = new ArrayList<LinhVuc>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				LinhVuc row1 = new LinhVuc (rowSet.getString(1), rowSet.getString(2));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}

		return rowList;
	}

	@Override
	public LinhVuc getByID(String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new LinhVuc (rowSet.getString(1),rowSet.getString(2));
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(LinhVuc t) {
		try {
			rowSet.moveToInsertRow();
			rowSet.updateString("maLV", t.getMaLinhVuc());
			rowSet.updateString("tenLV", t.getTenLinhVuc());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(LinhVuc t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getStringBySTT(1))) {
					rowSet.updateString(2, t.getStringBySTT(2));
					rowSet.updateRow();
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(LinhVuc t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2)))  	
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
	public List<LinhVuc> getByObj (LinhVuc t) {
		List<LinhVuc> resultList = new ArrayList<LinhVuc>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
					{
						LinhVuc tempObj = new LinhVuc (rowSet.getString(1), rowSet.getString(2));
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
