package dto;

import java.time.LocalDate;

//Khoa chinh: ma kenh, ngay, gio, phut
public class LichPhatSong {
	private String maKenh;	//1
	private String maCT;	//2
	private String thu;		//3
	
	private int thang;		//4
	private int ngay;		//5
	private int gio;		//6
	private int phut;		//7
	private int thoiLuong;	//8
	private int lanPhatSong;		//9
	private int ky_Tap;				//10
	
	
	public LichPhatSong(String maKenh, String maCT, String thu,int thang, int ngay, int gio, int phut, int thoiLuong, int lanPhatSong,
			int ky_Tap) {
		super();
		this.thang = thang;
		this.maKenh = maKenh;
		this.maCT = maCT;
		this.ngay = ngay;
		this.thu = thu;
		this.gio = gio;
		this.phut = phut;
		this.thoiLuong = thoiLuong;
		this.lanPhatSong = lanPhatSong;
		this.ky_Tap = ky_Tap;
	}



	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNumberOfCols () {
		return 9;
	}

	public String getMaKenh() {
		return maKenh;
	}

	public void setMaKenh(String maKenh) {
		this.maKenh = maKenh;
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public int getGio() {
		return gio;
	}

	public void setGio(int gio) {
		this.gio = gio;
	}

	public int getPhut() {
		return phut;
	}

	public void setPhut(int phut) {
		this.phut = phut;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public int getLanPhatSong() {
		return lanPhatSong;
	}

	public void setLanPhatSong(int lanPhatSong) {
		this.lanPhatSong = lanPhatSong;
	}

	public int getKy_Tap() {
		return ky_Tap;
	}

	public void setKy_Tap(int ky_Tap) {
		this.ky_Tap = ky_Tap;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maKenh;
		case 2: return maCT;
		case 3: return thu;
		}
		return null;
	}

	public int getIntBySTT (int i) {
		switch (i) {
		case 4: return thang;
		case 5: return ngay;
		case 6: return gio;
		case 7: return phut;
		case 8: return thoiLuong;
		case 9: return lanPhatSong;
		case 10: return ky_Tap;
		}
		return -1;
	}

	public int[] getStringFields ()
	{
		return new int[] {1, 2, 3};
	}

	public int[] getIntFields ()
	{
		return new int[] {4, 5, 6, 7, 8, 9, 10};
	}


	//to String

	@Override
	public String toString() {
		return "LichPhatSong{}";
	}
}
