package dto;


import javafx.beans.property.SimpleObjectProperty;

public class KenhTV {

	private String maKenh;		//1
	private String tenKenh;		//2
	private String tinhTP;		//3
	private String maLV;		//4
	private int thuocTU;
	

	public KenhTV(String maKenh, String tenKenh, String tinhTP, String maLV, int thuocTU) {
		this.maKenh = maKenh;
		this.tenKenh = tenKenh;
		this.thuocTU = thuocTU;
		this.maLV = maLV;
		this.tinhTP = tinhTP;
	}

	public KenhTV(){
		tenKenh = "";
	}
	
	public String getMaLV() {
		return maLV;
	}

	public void setMaLV(String maLV) {
		this.maLV = maLV;
	}

	public int getThuocTU() {
		return thuocTU;
	}

	public void setThuocTU(int thuocTU) {
		this.thuocTU = thuocTU;
	}

	public int getNumberOfCols () {
		return 4;
	}

	public String getTinhTP() {
		return tinhTP;
	}

	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}

	public String getMaKenh() {
		return maKenh;
	}

	public void setMaKenh(String maKenh) {
		this.maKenh = maKenh;
	}

	public String getTenKenh() {
		return tenKenh;
	}

	public void setTenKenh(String tenKenh) {
		this.tenKenh = tenKenh;
	}


	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maKenh;
		case 2: return tenKenh;
		case 3: return tinhTP;
		case 4: return maLV;
		}
		return null;
	}
	public int getIntBySTT (int i) {
		if (i == 5)
			return this.thuocTU;
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

	//Property
	public SimpleObjectProperty<Integer> getThuocTUProperty(){
		return  new SimpleObjectProperty<>(thuocTU);
	}


	//to String
	@Override
	public String toString() {
		return tenKenh;
	}
}
