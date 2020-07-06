package dto;

public class ChuongTrinh {
	private String maCT;		//1
	private String tenCT;		//2
	private String maTheLoai;	//3
	private String maNSX;		//4
	
	public ChuongTrinh(String maCT, String tenCT, String maTheLoai, String maNSX) {
		this.maCT = maCT;
		this.tenCT = tenCT;
		this.maTheLoai = maTheLoai;
		this.maNSX = maNSX;
	}

	public ChuongTrinh(){
		tenCT = "";
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public String getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(String maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getMaNSX() {
		return maNSX;
	}

	public void setMaNSX(String maNSX) {
		this.maNSX = maNSX;
	}
	public int getNumberOfCols () {
		return 4;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maCT;
		case 2: return tenCT;
		case 3: return maTheLoai;
		case 4: return maNSX;
		}
		return null;
	}

	public int getIntBySTT (int i) {	
		return -1;
	}

	public static int[] getStringFields ()
	{
		return new int[] {1, 2, 3, 4};
	}

	public static int[] getIntFields ()
	{
		return new int[0];
	}

	@Override
	public String toString() {
		return tenCT;
	}
}
