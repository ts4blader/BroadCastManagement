package dao;

import dto.ChuongTrinh;
import dto.KenhTV;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KenhTVDAO implements IDAO<KenhTV>{
	private static KenhTVDAO instance;
	
	private KenhTVDAO () {
	}
	
	public static KenhTVDAO getInstance () {
		if (instance == null)
		{
			instance = new KenhTVDAO();
		}
		return instance;
	}
	
	private JdbcRowSet rowSet = Connector.createRowSet("select * from KENHTV");
	private int[] strFields = ChuongTrinh.getStringFields();
	
	@Override
	public List<KenhTV> getAll() {
		List<KenhTV> rowList = new ArrayList<KenhTV>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				KenhTV row1 = new KenhTV (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getString(4), rowSet.getBoolean(5)? 1: 0);
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}
		return rowList;
	}

	@Override
	public KenhTV getByID(String id) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(id)) {
					return new KenhTV (rowSet.getString(1),rowSet.getString(2),rowSet.getString(3), rowSet.getString(4), rowSet.getBoolean(5)? 1: 0);
				}
			}
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean save(KenhTV t) {
		try {
			rowSet.moveToInsertRow();
			System.out.println(t.getStringBySTT(1));
			System.out.println(t.getStringBySTT(2));

			System.out.println(t.getStringBySTT(3));
			System.out.println(t.getStringBySTT(4));
			System.out.println(t.getIntBySTT(5));

			rowSet.updateString("makenh", t.getMaKenh());
			rowSet.updateString("tenkenh", t.getTenKenh());
			rowSet.updateString("tinhTP", t.getTinhTP());
			rowSet.updateString("maLV", t.getMaLV());
			rowSet.updateBoolean("thuocTU", t.getThuocTU()==1? true: false);
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(KenhTV t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getStringBySTT(1))) {
					rowSet.updateString(2, t.getStringBySTT(2));
					rowSet.updateString(3, t.getStringBySTT(3));
					rowSet.updateString(4, t.getMaLV());
					rowSet.updateBoolean(5, t.getThuocTU()==1? true: false);
					rowSet.updateRow();
				}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(KenhTV t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3)))
							if (t.getStringBySTT(4).equals("") || t.getStringBySTT(4).equals(rowSet.getString(4)))
								if (t.getThuocTU() == -1 || (t.getThuocTU()==1? true:false) == rowSet.getBoolean(5)) 
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
	public List<KenhTV> getByObj(KenhTV t) {
		List<KenhTV> resultList = new ArrayList<KenhTV>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3)))
							if (t.getStringBySTT(4).equals("") || t.getStringBySTT(4).equals(rowSet.getString(4)))
								if (t.getThuocTU() == -1 || (t.getThuocTU()==1? true:false) == rowSet.getBoolean(5))
								{
									KenhTV tempObj = new KenhTV (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getString(4), rowSet.getBoolean(5)? 1: 0);
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
