package dao;

import dto.QuocGia;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuocGiaDAO implements IDAO<QuocGia> {
	private static QuocGiaDAO instance;
	
	private QuocGiaDAO () {
	}
	
	public static QuocGiaDAO getInstance () {
		if (instance == null)
		{
			instance = new QuocGiaDAO();
		}
		return instance;
	}
	
	private JdbcRowSet rowSet = Connector.createRowSet("select * from QUOCGIA");

	@Override
	public List<QuocGia> getAll() {
		List<QuocGia> rowList = new ArrayList<QuocGia>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				QuocGia row1 = new QuocGia (rowSet.getString(1), rowSet.getString(2));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}

		return rowList;
	}

	@Override
	public QuocGia getByID(String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new QuocGia (rowSet.getString(1),rowSet.getString(2));
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(QuocGia t) {
		try {
			rowSet.moveToInsertRow();
			rowSet.updateString("MaQG", t.getMaQuocGia());
			rowSet.updateString("TenQG", t.getTenQuocGia());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(QuocGia t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getMaQuocGia())) {
					rowSet.updateString(2, t.getTenQuocGia());
					rowSet.updateRow();
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(QuocGia t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getMaQuocGia().equals("") || t.getMaQuocGia().equals(rowSet.getString("MaQG")))
					if (t.getTenQuocGia().equals("") || t.getTenQuocGia().equals(rowSet.getString("tenQG"))) {
						rowSet.deleteRow();
					}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}


	@Override
	public List<QuocGia> getByObj(QuocGia t) {
		List<QuocGia> resultList = new ArrayList<QuocGia>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getMaQuocGia().equals("") || t.getMaQuocGia().equals(rowSet.getString("maQG")))
					if (t.getTenQuocGia().equals("") || t.getTenQuocGia().equals(rowSet.getString("tenQG"))) {
						QuocGia tempObj = new QuocGia (rowSet.getString(1), rowSet.getString(2));
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
