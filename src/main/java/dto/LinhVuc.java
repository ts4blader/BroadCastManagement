package dto;

public class LinhVuc {
	private String maLinhVuc;	//1
	private String tenLinhVuc;	//2
	
	public String getMaLinhVuc() {
		return maLinhVuc;
	}

	public void setMaLinhVuc(String maLinhVuc) {
		this.maLinhVuc = maLinhVuc;
	}

	public String getTenLinhVuc() {
		return tenLinhVuc;
	}

	public void setTenLinhVuc(String tenLinhVuc) {
		this.tenLinhVuc = tenLinhVuc;
	}

	public LinhVuc(String maLinhVuc, String tenLinhVuc) {

		this.maLinhVuc = maLinhVuc;
		this.tenLinhVuc = tenLinhVuc;
	}

	public int getNumberOfCols () {
		return 2;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maLinhVuc;
		case 2: return tenLinhVuc;
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
		return tenLinhVuc;
	}
}
