package bank;

public class Bank {
	// 필드
	private String number; // 계좌번호
	private String owner; // 계좌주명
	private int password; // 계좌 비밀번호
	private int balance; // 계좌잔고

	// 생성자
	public Bank(String number, String owner, int password, int balance) {
		this.number = number;
		this.owner = owner;
		this.password = password;
		this.balance = balance;
	}

	// 게터&세터
	public String getOwner() {
		return owner;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return owner + " 고객님의 계좌: " + "[계좌번호=" + number + ", 계좌잔고=" + balance + "원]";
	}
}
