package dao;

import dto.TheLoai;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO implements IDAO<TheLoai> {
	private static TheLoaiDAO instance;
	
	private TheLoaiDAO () {
	}
	
	public static TheLoaiDAO getInstance () {
		if (instance == null)
		{
			instance = new TheLoaiDAO();
		}
		return instance;
	}

	private JdbcRowSet rowSet = Connector.createRowSet("select * from THELOAI");

	@Override
	public List<TheLoai> getAll(){
		List<TheLoai> rowList = new ArrayList<TheLoai>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				TheLoai row1 = new TheLoai (rowSet.getString(1), rowSet.getString(2));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}

		return rowList;
	}

	public TheLoai getByID (String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new TheLoai (rowSet.getString(1),rowSet.getString(2));
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(TheLoai theLoai) {
		try {
			rowSet.moveToInsertRow();
			rowSet.updateString("MATL", theLoai.getMaTheLoai());
			rowSet.updateString("TENTL", theLoai.getTenTheLoai());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}

	@Override
	public boolean update(TheLoai t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getMaTheLoai())) {
					rowSet.updateString(2, t.getTenTheLoai());
					rowSet.updateRow();
					
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(TheLoai t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getMaTheLoai().equals("") || t.getMaTheLoai().equals(rowSet.getString("matl")))
					if (t.getTenTheLoai().equals("") || t.getTenTheLoai().equals(rowSet.getString("tentl"))) {
						rowSet.deleteRow();
					}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<TheLoai> getByObj(TheLoai t) {
		List<TheLoai> resultList = new ArrayList<TheLoai>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getMaTheLoai().equals("") || rowSet.getString("matl").contains(t.getMaTheLoai()))
					if (t.getTenTheLoai().equals("") || rowSet.getString("tentl").contains(t.getTenTheLoai())) {
						TheLoai tempObj = new TheLoai (rowSet.getString(1), rowSet.getString(2));
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
