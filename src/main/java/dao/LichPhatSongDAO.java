package dao;

import dto.ChuongTrinh;
import dto.LichPhatSong;
import utilities.Connector;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LichPhatSongDAO implements IDAO<LichPhatSong> {
	private static LichPhatSongDAO instance;
	
	private LichPhatSongDAO () {
	}
	
	public static LichPhatSongDAO getInstance () {
		if (instance == null)
		{
			instance = new LichPhatSongDAO();
		}
		return instance;
	}
	
	private JdbcRowSet rowSet = Connector.createRowSet("select * from LICHPS");
	private int[] strFields = ChuongTrinh.getStringFields();
	private int[] intFields = ChuongTrinh.getIntFields();
	
	@Override
	public List<LichPhatSong> getAll() {
		List<LichPhatSong> rowList = new ArrayList<LichPhatSong>();
		try {
			rowSet.beforeFirst();
			while (rowSet.next()) {
				LichPhatSong row1 = new LichPhatSong (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getInt(4),rowSet.getInt(5),rowSet.getInt(6),rowSet.getInt(7),rowSet.getInt(8),rowSet.getInt(9), rowSet.getInt(10));
				rowList.add(row1);
			}
		} catch (SQLException e) {
			return null;
		}

		return rowList;
	}

	@Override
	public LichPhatSong getByID(String id) {
	
		return null;
	}

	@Override
	public boolean save(LichPhatSong t) {
		try {
			rowSet.moveToInsertRow();
			
			for (int i : strFields) {
				rowSet.updateString(i, t.getStringBySTT(i));
			}
			for (int i : intFields) {
				rowSet.updateInt(i, t.getIntBySTT(i));
			}
			
//			rowSet.updateString("maKenh", t.getMaKenh());
//			rowSet.updateString("maCT", t.getMaCT());
//			rowSet.updateString("Thu", t.getThu());
//			rowSet.updateInt("thang", t.getThang());
//			rowSet.updateInt("ngay", t.getNgay());
//			rowSet.updateInt("gio", t.getGio());
//			rowSet.updateInt("phut", t.getPhut());
//			rowSet.updateInt("thoiluong",t.getThoiLuong());
//			rowSet.updateInt("lanps", t.getLanPhatSong());
//			rowSet.updateInt("ky_tap", t.getKy_Tap());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(LichPhatSong t) {
		try {
			rowSet.beforeFirst();
			while (rowSet.next())
			{
				if (rowSet.getString(1).equals(t.getStringBySTT(1))) 
					if (rowSet.getInt("thang") == t.getThang() && rowSet.getInt("ngay") == t.getNgay() && rowSet.getInt("gio") == t.getGio() && rowSet.getInt("Phut") == t.getPhut())
					{
						for (int i : strFields) {
							rowSet.updateString(i, t.getStringBySTT(i));
						}
						for (int i : intFields) {
							rowSet.updateInt(i, t.getIntBySTT(i));
						}
//						rowSet.updateString(2, t.getStringBySTT(2));
//						rowSet.updateString(3, t.getStringBySTT(3));
//						rowSet.updateInt(4, t.getIntBySTT(4));
//						rowSet.updateInt(5, t.getIntBySTT(5));
//						rowSet.updateInt(6, t.getIntBySTT(6));
//						rowSet.updateInt(7, t.getIntBySTT(7));
//						rowSet.updateInt(8, t.getIntBySTT(8));
//						rowSet.updateInt(9, t.getIntBySTT(9));
//						rowSet.updateInt(10, t.getIntBySTT(10));
						rowSet.updateRow();
					}
			}
			return true;
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public boolean delete(LichPhatSong t) {
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
				for (int i: intFields) {
					if (t.getIntBySTT(i) == -1 || t.getIntBySTT(i) == rowSet.getInt(i)) {
						
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
//							if (t.getIntBySTT(4) == -1 || t.getIntBySTT(4) == rowSet.getInt(4))
//								if (t.getIntBySTT(5) == -1 || t.getIntBySTT(5) == rowSet.getInt(5))
//									if (t.getIntBySTT(6) == -1 || t.getIntBySTT(6) == rowSet.getInt(6))
//										if (t.getIntBySTT(7) == -1 || t.getIntBySTT(7) == rowSet.getInt(7))
//											if (t.getIntBySTT(8) == -1 || t.getIntBySTT(8) == rowSet.getInt(8))
//												if (t.getIntBySTT(9) == -1 || t.getIntBySTT(9) == rowSet.getInt(9))
//													if (t.getIntBySTT(10) == -1 || t.getIntBySTT(10) == rowSet.getInt(10))
//													{
//														rowSet.deleteRow();
//													}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<LichPhatSong> getByObj(LichPhatSong t) {
		List<LichPhatSong> resultList = new ArrayList<LichPhatSong>();
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
				for (int i: intFields) {
					if (t.getIntBySTT(i) == -1 || t.getIntBySTT(i) == rowSet.getInt(i)) {
						
					} else {
						isResult = false;
						break;
					}
				}
				if (isResult) {
					LichPhatSong tempObj = new LichPhatSong (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getInt(4),rowSet.getInt(5),rowSet.getInt(6),rowSet.getInt(7),rowSet.getInt(8),rowSet.getInt(9),rowSet.getInt(10));
					resultList.add(tempObj);
				}
				
				
//				if (t.getStringBySTT(1).equals("") || t.getStringBySTT(1).equals(rowSet.getString(1)))
//					if (t.getStringBySTT(2).equals("") || t.getStringBySTT(2).equals(rowSet.getString(2))) 
//						if (t.getStringBySTT(3).equals("") || t.getStringBySTT(3).equals(rowSet.getString(3))) 
//							if (t.getIntBySTT(4) == -1 || t.getIntBySTT(4) == rowSet.getInt(4))
//								if (t.getIntBySTT(5) == -1 || t.getIntBySTT(5) == rowSet.getInt(5))
//									if (t.getIntBySTT(6) == -1 || t.getIntBySTT(6) == rowSet.getInt(6))
//										if (t.getIntBySTT(7) == -1 || t.getIntBySTT(7) == rowSet.getInt(7))
//											if (t.getIntBySTT(8) == -1 || t.getIntBySTT(8) == rowSet.getInt(8))
//												if (t.getIntBySTT(9) == -1 || t.getIntBySTT(9) == rowSet.getInt(9))
//													if (t.getIntBySTT(10) == -1 || t.getIntBySTT(10) == rowSet.getInt(10))
//													{
//														LichPhatSong tempObj = new LichPhatSong (rowSet.getString(1), rowSet.getString(2), rowSet.getString(3), rowSet.getInt(4),rowSet.getInt(5),rowSet.getInt(6),rowSet.getInt(7),rowSet.getInt(8),rowSet.getInt(9),rowSet.getInt(10));
//														resultList.add(tempObj);
//													}
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
