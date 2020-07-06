package dto;

public class NhaSX {
	private String maNSX; 		//1
	private String tenNSX;		//2
	private String maQuocGia;	//3
	
	public NhaSX(String maNSX, String tenNSX, String maQuocGia) {
		super();
		this.maNSX = maNSX;
		this.tenNSX = tenNSX;
		this.maQuocGia = maQuocGia;
	}

	public NhaSX(){
		tenNSX = "";
	}

	public String getMaNSX() {
		return maNSX;
	}

	public void setMaNSX(String maNSX) {
		this.maNSX = maNSX;
	}

	public String getTenNSX() {
		return tenNSX;
	}

	public void setTenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}

	public String getMaQuocGia() {
		return maQuocGia;
	}

	public void setMaQuocGia(String maQuocGia) {
		this.maQuocGia = maQuocGia;
	}

	public int getNumberOfCols () {
		return 3;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maNSX;
		case 2: return tenNSX;
		case 3: return maQuocGia;
		}
		return null;
	}

	public int getIntBySTT (int i) {

		return -1;
	}

	public int[] getStringFields ()
	{
		return new int[] {1, 2, 3};
	}

	public int[] getIntFields ()
	{
		return new int[0];
	}


	//to String

	@Override
	public String toString() {
		return tenNSX;
	}
}
