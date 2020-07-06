package dao;

import dto.ChuongTrinh;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChuongTrinhDAO implements IDAO<ChuongTrinh> {
	
	private static ChuongTrinhDAO instance;
	
	private ChuongTrinhDAO () {
	}
	
	public static ChuongTrinhDAO getInstance () {
		if (instance == null)
		{
			instance = new ChuongTrinhDAO();
		}
		return instance;
	}
	
	private JdbcRowSet rowSet = Connector.createRowSet("select * from CHUONGTRINH");
	private int[] strFields = ChuongTrinh.getStringFields();

	@Override
	public List<ChuongTrinh> getAll() {
		List<ChuongTrinh> rowList = new ArrayList<ChuongTrinh>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				ChuongTrinh row1 = new ChuongTrinh (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getString(4));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}
		return rowList;
	}

	@Override
	public ChuongTrinh getByID(String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new ChuongTrinh (rowSet.getString(1),rowSet.getString(2),rowSet.getString(3), rowSet.getString(4));
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(ChuongTrinh t) {
		try {
			rowSet.moveToInsertRow();
//			for (int i : strFields) {
//				rowSet.updateString(i, t.getStringBySTT(i));
//			}
			rowSet.updateString("MACT", t.getMaCT());
			rowSet.updateString("TENCT", t.getTenCT());
			rowSet.updateString("MATL", t.getMaTheLoai());
			rowSet.updateString("MANSX", t.getMaNSX());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(ChuongTrinh t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getStringBySTT(1))) {
					for (int i: strFields) {
						if (i > 1) {
							rowSet.updateString(i, t.getStringBySTT(i));
						}
					}
//					rowSet.updateString(2, t.getStringBySTT(2));
//					rowSet.updateString(3, t.getStringBySTT(3));
//					rowSet.updateString(4, t.getStringBySTT(4));
					rowSet.updateRow();
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(ChuongTrinh t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				boolean isDelete = true;
				for (int i: strFields) {
					if (t.getStringBySTT(i).equals("") || t.getStringBySTT(i).equals(rowSet.getString(i))) {
						
					} else {
						isDelete = false;
						break;
					}
				}
				if (isDelete) {
					rowSet.deleteRow();
				}
				
//				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
//					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
//						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3)))
//							if (t.getStringBySTT(4).equals("") || t.getStringBySTT(3).equals(rowSet.getString(4))) 
//							{
//								rowSet.deleteRow();
//							}
			}
		} catch (SQLException e) {
			System.out.print(e);
			return false;
		}
		return true;
	}

	@Override
	public List<ChuongTrinh> getByObj(ChuongTrinh t) {
		List<ChuongTrinh> resultList = new ArrayList<ChuongTrinh>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				boolean isResult = true;
				for (int i: strFields) {
					if (t.getStringBySTT(i).equals("") || t.getStringBySTT(i).equals(rowSet.getString(i))) {
						
					} else {
						isResult = false;
						break;
					}
				}
				if (isResult) {
					ChuongTrinh tempObj = new ChuongTrinh (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getString(4));
					resultList.add(tempObj);
				}
//				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
//					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
//						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3)))
//							if (t.getStringBySTT(4).equals("") || t.getStringBySTT(4).equals(rowSet.getString(4)))
//							{
//								ChuongTrinh tempObj = new ChuongTrinh (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getString(4));
//								resultList.add(tempObj);
//							}
			}
			
		} catch (SQLException e) {return null;}
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
