package dto;

public class QuocGia {
	private String maQuocGia;	//1
	private String tenQuocGia;	//2
	
	public QuocGia(String maQuocGia, String tenQuocGia) {
		super();
		this.maQuocGia = maQuocGia;
		this.tenQuocGia = tenQuocGia;
	}
	public QuocGia(){
		tenQuocGia = "";
	}

	public String getMaQuocGia() {
		return maQuocGia;
	}

	public void setMaQuocGia(String maQuocGia) {
		this.maQuocGia = maQuocGia;
	}

	public String getTenQuocGia() {
		return tenQuocGia;
	}

	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia = tenQuocGia;
	}

	public int getNumberOfCols () {
		return 2;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maQuocGia;
		case 2: return tenQuocGia;
		}
		return null;
	}

	public int getIntBySTT (int i) {
		return -1;
	}

	public int[] getStringFields ()
	{
		return new int[] {1, 2};
	}

	public int[] getIntFields ()
	{
		return new int[0];
	}

	//to String

	@Override
	public String toString() {
		return tenQuocGia;
	}
}
